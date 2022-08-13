package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private Integer accId;
    private String authenticationToken;
    private String username;

    private List<AccountRoleDto> accountRoleDtos;
}
