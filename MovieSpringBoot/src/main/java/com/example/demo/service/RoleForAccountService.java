package com.example.demo.service;

import com.example.demo.dto.AccountRoleDto;
import com.example.demo.model.Account;
import com.example.demo.model.AccountRole;

import java.util.List;

public interface RoleForAccountService {
    List<AccountRoleDto> getRoleForAccount(int accId);

    void deleteRole(int userId);

    void addRoleForAccount(Account account, AccountRole accountRole);
}
