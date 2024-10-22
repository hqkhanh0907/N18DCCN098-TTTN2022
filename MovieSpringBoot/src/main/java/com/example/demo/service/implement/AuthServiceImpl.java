package com.example.demo.service.implement;

import com.example.demo.dto.*;
import com.example.demo.exception.MailException;
import com.example.demo.exception.UsernameExitException;
import com.example.demo.model.Account;
import com.example.demo.model.AccountRole;
import com.example.demo.model.VerificationToken;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AccountRoleRepository;
import com.example.demo.repository.VerificationTokenRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AccountService;
import com.example.demo.service.AuthService;
import com.example.demo.service.RoleForAccountService;
import com.example.demo.service.SendMailService;
import com.example.demo.util.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final SendMailService sendMailService;
    private final RoleForAccountService roleForAccountService;
    private final AccountRoleRepository accountRoleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountService accountService;
    private final AccountServiceImpl accountServiceImpl;

    @Override
    @Transactional
    public Account signup(RegisterRequest registerRequest) throws MailException, UsernameExitException {
        if (accountServiceImpl.checkEmail(registerRequest.getEmail(), registerRequest.getUsername()) == false
                && accountServiceImpl.checkUsername(registerRequest.getUsername()) == false) {
            Account user = new Account();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setEmail(registerRequest.getEmail());
            user.setLastname(registerRequest.getLastname());
            user.setFirstname(registerRequest.getFirstname());
            user.setEnable(false);
            user.setBirthday(registerRequest.getBirthday());
            user.setGender(registerRequest.isGender());
            accountRepository.save(user);
            Account account = accountRepository.findMovieAccountByUsername(registerRequest.getUsername());
            AccountRole accountRole = accountRoleRepository.getById(AppConstants.DEFAULT_ROLE_KEY_USER);
            GroupOfRolesKeyDto groupOfRolesKeyDto = new GroupOfRolesKeyDto(account.getId(), accountRole.getId());
            roleForAccountService.addRoleForAccount(groupOfRolesKeyDto);
            String token = generateVerificationToken(user);
            sendMailService.signup(user, token);
            return user;
        }
        return null;
    }

    @Override
    public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByTokenContent(token);
        fetchUserAndEnable(verificationToken);
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtTokenProvider.generateToken(authenticate);
        Account account = accountService.getAccountByUsername(loginRequest.getUsername());
        List<AccountRoleDto> accountRoleDtos = roleForAccountService.getRoleForAccount(account.getId());
        return new AuthenticationResponse(account.getId(), token, loginRequest.getUsername(), accountRoleDtos);

    }

    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        Account account = verificationToken.getAccount();
        account.setEnable(true);
        accountRepository.save(account);
    }

    private String generateVerificationToken(Account user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setTokenContent(token);
        verificationToken.setAccount(user);
        verificationToken.setCreatedTime(Instant.now());
        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
