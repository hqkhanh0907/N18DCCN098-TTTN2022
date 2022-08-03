package com.example.demo.service.implement;

import com.example.demo.dto.AccountRoleDto;
import com.example.demo.model.AccountRole;
import com.example.demo.repository.AccountRoleRepository;
import com.example.demo.service.AccountRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AccountRoleServiceImpl implements AccountRoleService {
    private final AccountRoleRepository accountRoleRepository;
    @Override
    public List<AccountRole> getAllAccountRole() {
        return accountRoleRepository.findAll();
    }

    @Override
    public List<AccountRoleDto> getAccountRolesForUsername(String username) {
        return null;
    }
}
