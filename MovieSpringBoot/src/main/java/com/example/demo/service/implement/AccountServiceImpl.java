package com.example.demo.service.implement;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.AccountPage;
import com.example.demo.dto.GroupOfRolesMapper;
import com.example.demo.dto.map.AccountMapper;
import com.example.demo.exception.AccountExeption;
import com.example.demo.exception.MailException;
import com.example.demo.exception.UsernameExitException;
import com.example.demo.model.Account;
import com.example.demo.model.AccountRole;
import com.example.demo.model.GroupOfRoles;
import com.example.demo.model.Key.GroupOfRolesKey;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AccountRoleRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.ImageService;
import com.example.demo.service.MovieEvaluateService;
import com.example.demo.service.RoleForAccountService;
import com.example.demo.service.SendMailService;
import com.example.demo.service.UserHistoryService;
import com.example.demo.service.VerificationTokenService;
import com.example.demo.util.AppConstants;
import com.example.demo.util.DataUtils;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.util.AppConstants.DEFAULT_IMAGE_ACCOUNTS;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMap;
    private final PasswordEncoder passwordEncoder;
    private final RoleForAccountService roleForAccountService;
    private final AccountRoleRepository accountRoleRepository;
    private final SendMailService sendMailService;
    private final UserHistoryService userHistoryService;
    private final VerificationTokenService verificationTokenService;
    private final MovieEvaluateService movieEvaluateService;
    private final ImageService imageService;
    private final GroupOfRolesMapper groupOfRolesMapper;

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map(account -> {
            return accountMap.accountToAccountDto(account);
        }).collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> getAccountByEnabled(boolean check) {
        List<AccountDto> accountDtoS = accountRepository.findAll().stream().map(account -> {
            if (account.getEnable() == check) {
                return accountMap.accountToAccountDto(account);
            }
            return null;
        }).collect(Collectors.toList());
        return accountDtoS;
    }

    @Override
    public List<AccountRole> getFullRoleOnAccount(Account account) {
        return account.getGroupOfRoleses().stream().map(groupOfRoles -> {
            return groupOfRoles.getAccountRole();
        }).collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccountById(int accountId) {
        return accountMap.accountToAccountDto(accountRepository.getById(accountId));
    }

    @Override
    public AccountDto getAccountByUsernameDTO(String accountUsername) {

        Account account = accountRepository.findMovieAccountByUsername(accountUsername);
        if (account == null) {
            throw new AccountExeption("Account not found");
        } else {
            return accountMap.accountToAccountDto(account);
        }
    }

    @Override
    public Account getAccountByUsername(String accountUsername) {
        Account account = accountRepository.findMovieAccountByUsername(accountUsername);
        if (account == null) {
            throw new AccountExeption("Account not found");
        } else {
            return account;
        }
    }

    @Override
    public boolean checkPasswordForAccount(Account account, String currentPassword) {
        if (account == null) {
            throw new AccountExeption("Account not found");
        } else {
            return passwordEncoder.matches(currentPassword, account.getPassword());
        }
    }

    @Override
    public Boolean changePasswordForAccount(Account account, String passwordHadChange) {
        account.setPassword(passwordEncoder.encode(passwordHadChange));
        accountRepository.save(account);
        return true;
    }

    @Override
    public AccountDto deleteAccountByUsername(String username) {
        Account account = accountRepository.findMovieAccountByUsername(username);
        if (account == null) {
            throw new AccountExeption("Account not found with username: " + username);
        } else {
            Integer id = account.getId();
            userHistoryService.deleteUserHistoryFromAccount(id);
            verificationTokenService.deleteUserTokens(id);
            movieEvaluateService.deleteMovieEvaluateByUserId(id);
            roleForAccountService.deleteRole(id);
            accountRepository.delete(account);
            return accountMap.accountToAccountDto(account);
        }
    }

    @Override
    public AccountDto editAccountByUsername(AccountDto accountDto) throws MailException {
        Account account = accountRepository.findById(accountDto.getId()).orElse(null);
        assert account != null;
        if (checkEmail(accountDto.getEmail(), account.getUsername()) != false) {
            throw new MailException("Email is exit");
        } else {
            accountRepository.save(accountMap.AccountDtoToAccount(accountDto));
            return accountDto;
        }
    }





    @Override
    public AccountDto createAccount(Account accountCreate, int roleId) throws
            MailException, UsernameExitException {
        Account account = new Account();
        //check mail bang fasle => email không tồn tại
        if (this.checkEmail(accountCreate.getEmail(), accountCreate.getUsername()) == false
                && this.checkUsername(accountCreate.getUsername()) == false) {
            String gPass = DataUtils.generateTempPwd(8);
            account.setUsername(accountCreate.getUsername());
            account.setPassword(passwordEncoder.encode(gPass));
            account.setEmail(accountCreate.getEmail());
            account.setLastname(accountCreate.getLastname());
            account.setFirstname(accountCreate.getFirstname());
            account.setBirthday(accountCreate.getBirthday());
            account.setGender(accountCreate.getGender());
            account.setAvatar(null);
            account.setEnable(accountCreate.getEnable());
            accountRepository.save(account);
            roleForAccountService.addRoleForAccount(
                    accountRepository.findMovieAccountByUsername(accountCreate.getUsername()),
                    accountRoleRepository.getById(roleId));
            accountRepository.save(account);
            sendMailService.create(account, gPass);
            return accountMap.accountToAccountDto(account);
        }
        return null;
    }

    @Override
    public AccountPage getAllUsersPage(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Page<Account> users = accountRepository.findAll(PageRequest.of(pageNo, pageSize, sort));

        List<AccountDto> content = users.getContent().stream().map(account -> {
            return accountMap.accountToAccountDto(account);
        }).collect(Collectors.toList());
        AccountPage accountPage = new AccountPage();
        accountPage.setAccountDtoS(content);
        accountPage.setPageNo(users.getNumber());
        accountPage.setPageSize(users.getSize());
        accountPage.setTotalElements(users.getTotalElements());
        accountPage.setTotalPages(users.getTotalPages());
        accountPage.setFirst(users.isFirst());
        accountPage.setLast(users.isLast());
        return accountPage;
    }

    @Override
    public boolean forgotPassword(Account account) {
        account.setPassword(DataUtils.generateTempPwd(8));
        sendMailService.forgotPassword(account, account.getPassword());
        account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12)));
        accountRepository.save(account);
        return true;
    }

    @Override
    public Account getAccountByEmail(String email) {
        for (Account account : new ArrayList<>(accountRepository.findAll())) {
            if (email.equals(account.getEmail())) {
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean saveImageAcc(MultipartFile image, int accId) {
        Account account = accountRepository.findById(accId).orElse(null);
        assert account != null;
        JSONObject path = imageService.uploadImage(image, account.getUsername(), DEFAULT_IMAGE_ACCOUNTS);
        account.setAvatar(path.getAsString("path"));
        accountRepository.save(account);
        return true;
    }

    public boolean checkEmail(String email, String username) throws MailException {
        for (Account account : new ArrayList<>(accountRepository.findAll())) {
            if (!account.getUsername().equals(username) && email.equals(account.getEmail())) {
                throw new MailException("User with email: " + email + " already existed");
            }
        }
        return false;
    }

    public boolean checkUsername(String username) throws UsernameExitException {
        for (Account userCheck : new ArrayList<>(accountRepository.findAll())) {
            if (username.equals(userCheck.getUsername())) {
                throw new UsernameExitException("Username is exit");
            }
        }
        return false;
    }

    private List<GroupOfRoles> setDefaultRole(int accId) {
        List<GroupOfRoles> groupOfRoles = new ArrayList<>();
        groupOfRoles.add(new GroupOfRoles(
                new GroupOfRolesKey(accId, AppConstants.DEFAULT_ROLE_KEY_USER),
                accountRepository.getById(accId),
                accountRoleRepository.getById(AppConstants.DEFAULT_ROLE_KEY_USER)
        ));
        return groupOfRoles;
    }
}
