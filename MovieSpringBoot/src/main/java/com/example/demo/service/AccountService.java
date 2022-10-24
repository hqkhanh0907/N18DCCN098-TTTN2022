package com.example.demo.service;


import com.example.demo.dto.*;
import com.example.demo.exception.MailException;
import com.example.demo.exception.UsernameExitException;
import com.example.demo.model.Account;
import com.example.demo.model.AccountRole;
import com.example.demo.model.Key.FavoriteMovieKey;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    List<AccountDto> getAccountByEnabled(boolean check);

    List<AccountRole> getFullRoleOnAccount(Account account);

    AccountDto getAccountById(Integer accountId);

    AccountDto getAccountByUsernameDTO(String accountUsername);

    Account getAccountByUsername(String accountUsername);

    boolean checkPasswordForAccount(Account account, String currentPassword);

    Boolean changePasswordForAccount(Account account, String passwordChange);

    AccountDto deleteAccountByUsername(String username);

    AccountDto editAccountById(AccountDto account) throws UsernameExitException, MailException;

    AccountDto createAccount(AccountDto accountDto) throws MailException, UsernameExitException;

    AccountPage getAllUsersPage(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    boolean forgotPassword(Account account);

    Account getAccountByEmail(String email);

    boolean saveImageAcc(MultipartFile image, Integer accId);

    Boolean follow(FavoriteMovieDto favoriteMovieDto);

    Boolean getFollow(FavoriteMovieKey favoriteMovieKey);

    Boolean saveHistory(AccountHistoryDto accountHistoryDto);

    List<MovieDto> getMovieHistoryByAccId(Integer accId);

    List<MovieDto> getMovieFavoriteByAccId(Integer accId);

    List<BillingInformationDto> getBillByAccId(Integer accId);

    Integer getNumAccount();

    Map<String, List<?>> getPieAcc();
}
