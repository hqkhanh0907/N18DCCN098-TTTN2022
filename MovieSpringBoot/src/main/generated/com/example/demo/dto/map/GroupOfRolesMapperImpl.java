package com.example.demo.dto.map;

import com.example.demo.dto.AccountRoleDto;
import com.example.demo.dto.GroupOfRolesDto;
import com.example.demo.dto.GroupOfRolesKeyDto;
import com.example.demo.model.AccountRole;
import com.example.demo.model.GroupOfRoles;
import com.example.demo.model.Key.GroupOfRolesKey;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class GroupOfRolesMapperImpl implements GroupOfRolesMapper {

    @Override
    public GroupOfRoles groupOfRolesDtoToGroupOfRoles(GroupOfRolesDto groupOfRolesDto) {
        if ( groupOfRolesDto == null ) {
            return null;
        }

        GroupOfRoles groupOfRoles = new GroupOfRoles();

        groupOfRoles.setId( groupOfRolesKeyDtoToGroupOfRolesKey( groupOfRolesDto.getId() ) );
        groupOfRoles.setAccountRole( accountRoleDtoToAccountRole( groupOfRolesDto.getAccountRole() ) );

        return groupOfRoles;
    }

    @Override
    public GroupOfRolesDto groupOfRolesToGroupOfRolesDto(GroupOfRoles groupOfRoles) {
        if ( groupOfRoles == null ) {
            return null;
        }

        GroupOfRolesDto groupOfRolesDto = new GroupOfRolesDto();

        groupOfRolesDto.setId( groupOfRolesKeyToGroupOfRolesKeyDto( groupOfRoles.getId() ) );
        groupOfRolesDto.setAccountRole( accountRoleToAccountRoleDto( groupOfRoles.getAccountRole() ) );

        return groupOfRolesDto;
    }

    @Override
    public GroupOfRoles updateGroupOfRolesFromGroupOfRolesDto(GroupOfRolesDto groupOfRolesDto, GroupOfRoles groupOfRoles) {
        if ( groupOfRolesDto == null ) {
            return null;
        }

        if ( groupOfRolesDto.getId() != null ) {
            if ( groupOfRoles.getId() == null ) {
                groupOfRoles.setId( new GroupOfRolesKey() );
            }
            groupOfRolesKeyDtoToGroupOfRolesKey1( groupOfRolesDto.getId(), groupOfRoles.getId() );
        }
        if ( groupOfRolesDto.getAccountRole() != null ) {
            if ( groupOfRoles.getAccountRole() == null ) {
                groupOfRoles.setAccountRole( new AccountRole() );
            }
            accountRoleDtoToAccountRole1( groupOfRolesDto.getAccountRole(), groupOfRoles.getAccountRole() );
        }

        return groupOfRoles;
    }

    protected GroupOfRolesKey groupOfRolesKeyDtoToGroupOfRolesKey(GroupOfRolesKeyDto groupOfRolesKeyDto) {
        if ( groupOfRolesKeyDto == null ) {
            return null;
        }

        GroupOfRolesKey groupOfRolesKey = new GroupOfRolesKey();

        groupOfRolesKey.setAccountId( groupOfRolesKeyDto.getAccountId() );
        groupOfRolesKey.setRoleId( groupOfRolesKeyDto.getRoleId() );

        return groupOfRolesKey;
    }

    protected AccountRole accountRoleDtoToAccountRole(AccountRoleDto accountRoleDto) {
        if ( accountRoleDto == null ) {
            return null;
        }

        AccountRole accountRole = new AccountRole();

        accountRole.setId( accountRoleDto.getId() );
        accountRole.setName( accountRoleDto.getName() );

        return accountRole;
    }

    protected GroupOfRolesKeyDto groupOfRolesKeyToGroupOfRolesKeyDto(GroupOfRolesKey groupOfRolesKey) {
        if ( groupOfRolesKey == null ) {
            return null;
        }

        GroupOfRolesKeyDto groupOfRolesKeyDto = new GroupOfRolesKeyDto();

        groupOfRolesKeyDto.setAccountId( groupOfRolesKey.getAccountId() );
        groupOfRolesKeyDto.setRoleId( groupOfRolesKey.getRoleId() );

        return groupOfRolesKeyDto;
    }

    protected AccountRoleDto accountRoleToAccountRoleDto(AccountRole accountRole) {
        if ( accountRole == null ) {
            return null;
        }

        AccountRoleDto accountRoleDto = new AccountRoleDto();

        accountRoleDto.setId( accountRole.getId() );
        accountRoleDto.setName( accountRole.getName() );

        return accountRoleDto;
    }

    protected void groupOfRolesKeyDtoToGroupOfRolesKey1(GroupOfRolesKeyDto groupOfRolesKeyDto, GroupOfRolesKey mappingTarget) {
        if ( groupOfRolesKeyDto == null ) {
            return;
        }

        if ( groupOfRolesKeyDto.getAccountId() != null ) {
            mappingTarget.setAccountId( groupOfRolesKeyDto.getAccountId() );
        }
        if ( groupOfRolesKeyDto.getRoleId() != null ) {
            mappingTarget.setRoleId( groupOfRolesKeyDto.getRoleId() );
        }
    }

    protected void accountRoleDtoToAccountRole1(AccountRoleDto accountRoleDto, AccountRole mappingTarget) {
        if ( accountRoleDto == null ) {
            return;
        }

        if ( accountRoleDto.getId() != null ) {
            mappingTarget.setId( accountRoleDto.getId() );
        }
        if ( accountRoleDto.getName() != null ) {
            mappingTarget.setName( accountRoleDto.getName() );
        }
    }
}
