package com.example.demo.service.implement;

import com.example.demo.dto.*;
import com.example.demo.dto.map.AccountHistoryMapper;
import com.example.demo.dto.map.AccountMapper;
import com.example.demo.dto.map.GroupOfRolesMapper;
import com.example.demo.exception.AccountExeption;
import com.example.demo.exception.MailException;
import com.example.demo.exception.UsernameExitException;
import com.example.demo.model.*;
import com.example.demo.model.Key.FavoriteMovieKey;
import com.example.demo.model.Key.GroupOfRolesKey;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AccountRoleRepository;
import com.example.demo.repository.FavoriteMovieRepository;
import com.example.demo.repository.MovieDetailRepository;
import com.example.demo.service.*;
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
import java.util.Objects;
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
    private final MovieDetailRepository movieDetailRepository;
    private final FavoriteMovieRepository favoriteMovieRepository;
    private final AccountHistoryMapper accountHistoryMapper;
    private final GroupOfRolesMapper groupOfRolesMapper;

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map(account -> {
            AccountDto accountDto = accountMap.accountToAccountDto(account);
            List<GroupOfRolesDto> groupOfRolesDtos = account.getGroupOfRoleses().stream().map(groupOfRoles -> {
                return groupOfRolesMapper.groupOfRolesToGroupOfRolesDto(groupOfRoles);
            }).collect(Collectors.toList());
            accountDto.setGroupOfRolesDtos(groupOfRolesDtos);
            return accountDto;
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
    public AccountDto getAccountById(Integer accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        List<GroupOfRolesDto> groupOfRolesDtos = account.getGroupOfRoleses().stream().map(
                groupOfRoles -> {
                    return groupOfRolesMapper.groupOfRolesToGroupOfRolesDto(groupOfRoles);
                }).collect(Collectors.toList());
        AccountDto accountDto = accountMap.accountToAccountDto(account);
        accountDto.setGroupOfRolesDtos(groupOfRolesDtos);
        return accountDto;
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
    public AccountDto editAccountById(AccountDto accountDto) throws MailException {
        Account account = accountRepository.findById(accountDto.getId()).orElse(null);
        if (checkEmail(accountDto.getEmail(), account.getUsername()) != false) {
            throw new MailException("Email is exit");
        } else {
            if (!Objects.isNull(account.getGroupOfRoleses()) && !account.getGroupOfRoleses().isEmpty()) {
                roleForAccountService.deleteRole(accountDto.getId());
            }
            account.setFirstname(accountDto.getFirstname());
            account.setEmail(accountDto.getEmail());
            account.setEnable(accountDto.getEnable());
            account.setPhoneNumber(accountDto.getPhoneNumber());
            account.setBirthday(accountDto.getBirthday());
            account.setGender(accountDto.getGender());
            account.setLastname(accountDto.getLastname());
            if (!Objects.isNull(accountDto.getWardId())) {
                account.setWardId(accountDto.getWardId());
            }
            if (!Objects.isNull(accountDto.getAddressDetails())) {
                account.setAddressDetails(accountDto.getAddressDetails());
            }
            if (!Objects.isNull(accountDto.getGroupOfRolesDtos())) {
//                account.setGroupOfRoleses(
//                        accountDto
//                                .getGroupOfRolesDtos()
//                                .stream().map(groupOfRolesDto -> {
//                                    GroupOfRoles groupOfRoles = new GroupOfRoles();
//                                    GroupOfRolesKey groupOfRolesKey = new GroupOfRolesKey(
//                                            groupOfRolesDto.getId().getAccountId(),
//                                            groupOfRolesDto.getId().getRoleId());
//                                    AccountRole accountRole = accountRoleRepository
//                                            .findById(groupOfRolesDto.getId().getRoleId()).orElse(null);
//                                    groupOfRoles.setId(groupOfRolesKey);
//                                    groupOfRoles.setAccount(account);
//                                    groupOfRoles.setAccountRole(accountRole);
//                                    return groupOfRoles;
//                                }).collect(Collectors.toList()));
                for (GroupOfRolesDto groupOfRolesDto : accountDto.getGroupOfRolesDtos()) {
                    roleForAccountService.addRoleForAccount(groupOfRolesDto.getId());
                }
            }
            accountRepository.save(account);
            return accountDto;
        }
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) throws
            MailException, UsernameExitException {
        Account account = new Account();
        //check mail bang fasle => email không tồn tại
        if (this.checkEmail(accountDto.getEmail(), accountDto.getUsername()) == false
                && this.checkUsername(accountDto.getUsername()) == false) {
            String gPass = DataUtils.generateTempPwd(8);
            account.setUsername(accountDto.getUsername());
            account.setPassword(passwordEncoder.encode(gPass));
            account.setEmail(accountDto.getEmail());
            account.setLastname(accountDto.getLastname());
            account.setFirstname(accountDto.getFirstname());
            account.setEnable(accountDto.getEnable());
            if (Objects.isNull(accountDto.getBirthday())) {
                account.setBirthday(accountDto.getBirthday());
            }
            account.setGender(accountDto.getGender());
            accountRepository.save(account);
            for (GroupOfRolesDto groupOfRolesDto : accountDto.getGroupOfRolesDtos()) {
                roleForAccountService.addRoleForAccount(groupOfRolesDto.getId());
            }
            if (account.getEnable()) {
                sendMailService.create(account, gPass);
            }
            return accountMap.accountToAccountDto(account);
        }
        return null;
    }

    @Override
    public AccountPage getAllUsersPage(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
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
    public boolean saveImageAcc(MultipartFile image, Integer accId) {
        Account account = accountRepository.findById(accId).orElse(null);
        assert account != null;
        JSONObject path = imageService.uploadImage(image, account.getUsername(), DEFAULT_IMAGE_ACCOUNTS);
        account.setAvatar(path.getAsString("path"));
        accountRepository.save(account);
        return true;
    }

    @Override
    public Boolean follow(FavoriteMovieDto favoriteMovieDto) {
        try {
            Account account = accountRepository.findById(favoriteMovieDto.getId().getAccountId()).orElse(null);
            List<FavoriteMovie> favoriteMovies = account.getFavoriteMovies();
            FavoriteMovie favoriteMovie = new FavoriteMovie();
            //new key
            FavoriteMovieKey favoriteMovieKey = new FavoriteMovieKey();
            favoriteMovieKey.setMovieId(favoriteMovieDto.getId().getMovieId());
            favoriteMovieKey.setAccountId(favoriteMovieDto.getId().getAccountId());
            //new favorite
            favoriteMovie.setId(favoriteMovieKey);
            favoriteMovie.setMovie(movieDetailRepository.findById(favoriteMovieDto.getId().getMovieId()).orElse(null));
            favoriteMovie.setAccount(account);
            favoriteMovie.setDate(favoriteMovieDto.getDate());
            if (Objects.isNull(favoriteMovies) || favoriteMovies.isEmpty()) {
                favoriteMovies.add(favoriteMovie);
            } else {
                Boolean checkExit = false;
                for (FavoriteMovie favoriteMovieCheck : favoriteMovies) {
                    if (favoriteMovieCheck.getId().equals(favoriteMovieKey)) {
                        checkExit = true;
                        break;
                    }
                }
                if (checkExit.equals(false)) {
                    favoriteMovies.add(favoriteMovie);
                }
            }
            account.setFavoriteMovies(favoriteMovies);
            accountRepository.save(account);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean getFollow(FavoriteMovieKey favoriteMovieKey) {
        try {
            Account account = accountRepository.findById(favoriteMovieKey.getAccountId()).orElse(null);
            FavoriteMovie favoriteMovie = favoriteMovieRepository.findById(favoriteMovieKey).orElse(null);
            if (account.getFavoriteMovies().contains(favoriteMovie)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean saveHistory(AccountHistoryDto accountHistoryDto) {
        try {
            Account account = accountRepository.findById(accountHistoryDto.getAccountHistoryKey().getAccountId()).orElse(null);
            Movie movie = movieDetailRepository.findById(accountHistoryDto.getAccountHistoryKey().getAccountId()).orElse(null);
            AccountHistory accountHistory = accountHistoryMapper.accountHistoryDtoToAccountHistory(accountHistoryDto);
            accountHistory.setAccount(account);
            accountHistory.setMovie(movie);
            List<AccountHistory> accountHistories = account.getAccountHistories();
            if (Objects.isNull(accountHistories) || accountHistories.isEmpty()) {
                accountHistories.add(accountHistory);
            } else {
                accountHistories.stream().map(accountHistoryCheck -> {
                    if ((accountHistoryCheck.getAccountHistoryKey().getAccountId() == accountHistory.getAccountHistoryKey().getAccountId()) &&
                            (accountHistoryCheck.getAccountHistoryKey().getMovieId() == accountHistory.getAccountHistoryKey().getMovieId())) {
                        accountHistoryCheck.setDate(accountHistory.getDate());
                        accountHistoryCheck.setTime_watched(accountHistory.getTime_watched());
                    }
                    return accountHistoryCheck;
                }).collect(Collectors.toList());
            }
            account.setAccountHistories(accountHistories);
            accountRepository.save(account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
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

    private List<GroupOfRoles> setDefaultRole(Integer accId) {
        List<GroupOfRoles> groupOfRoles = new ArrayList<>();
        groupOfRoles.add(new GroupOfRoles(
                new GroupOfRolesKey(accId, AppConstants.DEFAULT_ROLE_KEY_USER),
                accountRepository.getById(accId),
                accountRoleRepository.getById(AppConstants.DEFAULT_ROLE_KEY_USER)
        ));
        return groupOfRoles;
    }
}
