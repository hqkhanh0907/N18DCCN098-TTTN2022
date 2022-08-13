package com.example.demo.service;

import com.example.demo.dto.AccountHistoryDto;
import com.example.demo.model.Key.AccountHistoryKey;

import java.util.List;

public interface UserHistoryService {
    String createHistory(AccountHistoryDto userHistoryDTO);
    void deleteUserHistoryFromAccount(Integer userId);

    AccountHistoryDto addHistory(Integer idAcc, Integer idMovie);

    List<AccountHistoryDto> getAllByAccount(Integer id);

    AccountHistoryDto getHistoryById(AccountHistoryKey accountHistoryKey);
}
