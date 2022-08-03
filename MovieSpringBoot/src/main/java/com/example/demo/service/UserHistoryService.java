package com.example.demo.service;

import com.example.demo.dto.AccountHistoryDto;

import java.util.List;

public interface UserHistoryService {
    String createHistory(AccountHistoryDto userHistoryDTO);
    void deleteUserHistoryFromAccount(int userId);

    AccountHistoryDto addHistory(Integer idAcc, Integer idMovie);

    List<AccountHistoryDto> getAllByAccount(int id);
}
