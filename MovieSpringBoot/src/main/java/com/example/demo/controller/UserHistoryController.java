package com.example.demo.controller;

import com.example.demo.model.Key.AccountHistoryKey;
import com.example.demo.service.UserHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/history")
public class UserHistoryController {
    private final UserHistoryService userHistoryService;

    @GetMapping("/add-history/{idAcc}/{idMovie}")
    public ResponseEntity<?> addHistory(@PathVariable("idAcc") Integer idAcc, @PathVariable("idMovie") Integer idMovie) {
        return new ResponseEntity<>(userHistoryService.addHistory(idAcc, idMovie), HttpStatus.OK);
    }

    @GetMapping("/get-all-by-account/{id}")
    public ResponseEntity<?> getAllByAccount(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userHistoryService.getAllByAccount(id), HttpStatus.OK);
    }

    @PostMapping("/get-history-by-id")
    public ResponseEntity<?> getHistoryById(@RequestBody AccountHistoryKey accountHistoryKey) {
        return new ResponseEntity<>(userHistoryService.getHistoryById(accountHistoryKey), HttpStatus.OK);
    }
}
