package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto implements Serializable {
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Boolean enable;
    @NotNull
    private String email;
    private String avatar;
    @NotNull
    private String lastname;
    @NotNull
    private String firstname;
    @NotNull
    private Date birthday;
    private String wardId;
    private String addressDetails;
    private String phoneNumber;
    @NotNull
    private Boolean gender;
    private List<GroupOfRolesDto> groupOfRolesDtos;
}
