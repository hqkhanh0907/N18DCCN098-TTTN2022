package com.example.demo.dto.map;

import com.example.demo.dto.GroupOfRolesDto;
import com.example.demo.model.GroupOfRoles;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GroupOfRolesMapper {
    GroupOfRoles groupOfRolesDtoToGroupOfRoles(GroupOfRolesDto groupOfRolesDto);

    GroupOfRolesDto groupOfRolesToGroupOfRolesDto(GroupOfRoles groupOfRoles);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GroupOfRoles updateGroupOfRolesFromGroupOfRolesDto(GroupOfRolesDto groupOfRolesDto, @MappingTarget GroupOfRoles groupOfRoles);
}
