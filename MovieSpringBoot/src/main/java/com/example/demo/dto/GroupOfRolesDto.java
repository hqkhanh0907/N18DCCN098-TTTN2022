package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupOfRolesDto implements Serializable {
    private GroupOfRolesKeyDto id = new GroupOfRolesKeyDto();
    private AccountRoleDto accountRole = new AccountRoleDto();
}
