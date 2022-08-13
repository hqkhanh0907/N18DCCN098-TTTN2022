package com.example.demo.repository;

import com.example.demo.model.AccountHistory;
import com.example.demo.model.Key.AccountHistoryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, AccountHistoryKey> {
}