package com.example.demo.repository;

import com.example.demo.model.AccountHistory;
import com.example.demo.model.Key.AccountHistoryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, AccountHistoryKey> {
    @Transactional
    @Modifying
    @Query(value = "delete from account_history hs where hs.account_id=? and hs.movie_id=?", nativeQuery = true)
    void deleteAccountHistory(Integer accountId, Integer movieId);

    @Transactional
    @Modifying
    @Query(value = "insert into account_history(`account_id`,`movie_id`,`time_watched`,`date`) values (?,?,?,?)", nativeQuery = true)
    void saveAccountHistory(Integer accountId, Integer movieId, Float timeWatch, Date date);

    @Transactional
    @Modifying
    @Query(value = "update account_history a set a.account_id=:accountId, a.movie_id=:movieId, a.time_watched=:timeWatch, a.date=:date " +
            "where a.account_id=:accountId and a.movie_id=:movieId", nativeQuery = true)
    void updateAccountHistory(@Param("accountId") Integer accountId, @Param("movieId") Integer movieId, @Param("timeWatch") Float timeWatch, @Param("date") Date date);
}