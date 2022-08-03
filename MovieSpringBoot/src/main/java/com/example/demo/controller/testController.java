package com.example.demo.controller;

import com.example.demo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class testController {
    private final AccountService accountService;

//    @GetMapping("/getAcc")
//    public ResponseEntity<List<AccountDto>> getAllAcc() {
//        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
//    }
}
