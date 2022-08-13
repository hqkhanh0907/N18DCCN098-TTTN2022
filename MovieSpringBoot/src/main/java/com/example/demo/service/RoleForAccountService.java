package com.example.demo.service;

import com.example.demo.dto.AccountRoleDto;
import com.example.demo.dto.GroupOfRolesKeyDto;

import java.util.List;

public interface RoleForAccountService {
    List<AccountRoleDto> getRoleForAccount(Integer accId);

    void deleteRole(Integer userId);

    void addRoleForAccount(GroupOfRolesKeyDto groupOfRolesKeyDto);
}
