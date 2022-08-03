package com.example.demo.service;


import com.example.demo.dto.AccountDto;
import com.example.demo.dto.AccountPage;
import com.example.demo.exception.MailException;
import com.example.demo.exception.UsernameExitException;
import com.example.demo.model.Account;
import com.example.demo.model.AccountRole;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    List<AccountDto> getAccountByEnabled(boolean check);

    List<AccountRole> getFullRoleOnAccount(Account account);

    AccountDto getAccountById(int accountId);

    AccountDto getAccountByUsernameDTO(String accountUsername);

    Account getAccountByUsername(String accountUsername);

    boolean checkPasswordForAccount(Account account, String currentPassword);

    Boolean changePasswordForAccount(Account account, String passwordChange);

    AccountDto deleteAccountByUsername(String username);

    AccountDto editAccountByUsername(AccountDto account) throws UsernameExitException, MailException;

    AccountDto createAccount(Account account, int roleId) throws MailException, UsernameExitException;

    AccountPage getAllUsersPage(int pageNo, int pageSize, String sortBy, String sortDir);

    boolean forgotPassword(Account account);

    Account getAccountByEmail(String email);

    boolean saveImageAcc(MultipartFile image, int accId);
}
