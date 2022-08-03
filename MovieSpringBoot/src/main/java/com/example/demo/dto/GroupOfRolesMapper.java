package com.example.demo.dto;

import com.example.demo.model.GroupOfRoles;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GroupOfRolesMapper {
    GroupOfRoles groupOfRolesDtoToGroupOfRoles(GroupOfRolesDto groupOfRolesDto);

    GroupOfRolesDto groupOfRolesToGroupOfRolesDto(GroupOfRoles groupOfRoles);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GroupOfRoles updateGroupOfRolesFromGroupOfRolesDto(GroupOfRolesDto groupOfRolesDto, @MappingTarget GroupOfRoles groupOfRoles);
}
