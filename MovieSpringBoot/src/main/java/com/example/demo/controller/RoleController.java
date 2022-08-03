package com.example.demo.controller;

import com.example.demo.model.AccountRole;
import com.example.demo.service.AccountRoleService;
import com.example.demo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final AccountRoleService accountRoleService;

    @GetMapping("/getAllRole")
    public ResponseEntity<List<AccountRole>> getAllAcc() {
        return new ResponseEntity<>(accountRoleService.getAllAccountRole(), HttpStatus.OK);
    }
}
