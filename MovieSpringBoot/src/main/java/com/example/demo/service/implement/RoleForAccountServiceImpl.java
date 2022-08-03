package com.example.demo.service.implement;

import com.example.demo.dto.AccountRoleDto;
import com.example.demo.dto.map.AccountRoleMapper;
import com.example.demo.model.Account;
import com.example.demo.model.AccountRole;
import com.example.demo.model.GroupOfRoles;
import com.example.demo.model.Key.GroupOfRolesKey;
import com.example.demo.repository.GroupOfRolesRepository;
import com.example.demo.service.RoleForAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleForAccountServiceImpl implements RoleForAccountService {
    private final GroupOfRolesRepository groupOfRolesRepository;
    private final AccountRoleMapper accountRoleMapper;

    @Override
    public void addRoleForAccount(Account account, AccountRole accountRole) {
        GroupOfRolesKey groupOfRolesKey = new GroupOfRolesKey();
        groupOfRolesKey.setAccountId(account.getId());
        groupOfRolesKey.setRoleId(accountRole.getId());
        GroupOfRoles groupOfRoles = new GroupOfRoles();
        groupOfRoles.setId(groupOfRolesKey);
        groupOfRoles.setAccountRole(accountRole);
        groupOfRoles.setAccount(account);
        groupOfRolesRepository.save(groupOfRoles);
    }

    @Override
    public List<AccountRoleDto> getRoleForAccount(int accId) {
        return groupOfRolesRepository.findAll().stream().map(groupRoles -> {
            return accountRoleMapper.accountRoleToAccountRoleDto(groupRoles.getAccountRole());
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteRole(int userId) {
        List<GroupOfRoles> groupOfRoles = groupOfRolesRepository.findAll();
        groupOfRoles.forEach(groupOfRole -> {
            if (groupOfRole.getAccount().getId() == userId) {
                groupOfRolesRepository.delete(groupOfRole);
            }
        });
    }
}
