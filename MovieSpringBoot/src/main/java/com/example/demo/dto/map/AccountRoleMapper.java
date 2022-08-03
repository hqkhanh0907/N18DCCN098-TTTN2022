package com.example.demo.dto.map;

import com.example.demo.dto.AccountRoleDto;
import com.example.demo.model.AccountRole;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AccountRoleMapper {
    AccountRole accountRoleDtoToAccountRole(AccountRoleDto accountRoleDto);

    AccountRoleDto accountRoleToAccountRoleDto(AccountRole accountRole);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountRole updateAccountRoleFromAccountRoleDto(AccountRoleDto accountRoleDto, @MappingTarget AccountRole accountRole);

}
