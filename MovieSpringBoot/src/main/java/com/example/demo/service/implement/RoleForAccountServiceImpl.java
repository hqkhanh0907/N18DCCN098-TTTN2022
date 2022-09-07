package com.example.demo.service.implement;

import com.example.demo.dto.AccountRoleDto;
import com.example.demo.dto.GroupOfRolesKeyDto;
import com.example.demo.dto.map.AccountRoleMapper;
import com.example.demo.model.GroupOfRoles;
import com.example.demo.repository.GroupOfRolesRepository;
import com.example.demo.service.RoleForAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleForAccountServiceImpl implements RoleForAccountService {
    private final GroupOfRolesRepository groupOfRolesRepository;
    private final AccountRoleMapper accountRoleMapper;

    @Override
    public void addRoleForAccount(GroupOfRolesKeyDto groupOfRolesKeyDto) {
        try {
            groupOfRolesRepository.saveRoleOfAccount(groupOfRolesKeyDto.getAccountId(), groupOfRolesKeyDto.getRoleId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AccountRoleDto> getRoleForAccount(Integer accId) {
        List<AccountRoleDto> accountRoleDtos = new ArrayList<>();
        for (GroupOfRoles groupOfRoles: groupOfRolesRepository.findAll()){
            if (groupOfRoles.getId().getAccountId() == accId) {
                accountRoleDtos.add(accountRoleMapper.accountRoleToAccountRoleDto(groupOfRoles.getAccountRole()));
            }
        }
        return accountRoleDtos;
    }

    @Override
    public void deleteRole(Integer userId) {
        List<GroupOfRoles> groupOfRoles = groupOfRolesRepository.findAll();
        groupOfRoles.forEach(groupOfRole -> {
            if (groupOfRole.getAccount().getId() == userId) {
                groupOfRolesRepository.deleteRoleById(groupOfRole.getId().getAccountId(), groupOfRole.getId().getRoleId());
            }
        });
    }
}
