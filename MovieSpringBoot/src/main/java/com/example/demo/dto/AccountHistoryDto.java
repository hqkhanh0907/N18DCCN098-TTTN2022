package com.example.demo.dto;

import com.example.demo.dto.key.AccountHistoryKeyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistoryDto implements Serializable {
    private AccountHistoryKeyDto accountHistoryKey;
    private AccountDto account;
    private MovieDto movie;
    private Date date;
    private Float time_watched;
}
