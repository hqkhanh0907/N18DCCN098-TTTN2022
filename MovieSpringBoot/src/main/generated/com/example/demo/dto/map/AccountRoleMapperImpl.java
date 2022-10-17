package com.example.demo.dto.map;

import com.example.demo.dto.AccountRoleDto;
import com.example.demo.model.AccountRole;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class AccountRoleMapperImpl implements AccountRoleMapper {

    @Override
    public AccountRole accountRoleDtoToAccountRole(AccountRoleDto accountRoleDto) {
        if ( accountRoleDto == null ) {
            return null;
        }

        AccountRole accountRole = new AccountRole();

        accountRole.setId( accountRoleDto.getId() );
        accountRole.setName( accountRoleDto.getName() );

        return accountRole;
    }

    @Override
    public AccountRoleDto accountRoleToAccountRoleDto(AccountRole accountRole) {
        if ( accountRole == null ) {
            return null;
        }

        AccountRoleDto accountRoleDto = new AccountRoleDto();

        accountRoleDto.setId( accountRole.getId() );
        accountRoleDto.setName( accountRole.getName() );

        return accountRoleDto;
    }

    @Override
    public AccountRole updateAccountRoleFromAccountRoleDto(AccountRoleDto accountRoleDto, AccountRole accountRole) {
        if ( accountRoleDto == null ) {
            return null;
        }

        if ( accountRoleDto.getId() != null ) {
            accountRole.setId( accountRoleDto.getId() );
        }
        if ( accountRoleDto.getName() != null ) {
            accountRole.setName( accountRoleDto.getName() );
        }

        return accountRole;
    }
}
