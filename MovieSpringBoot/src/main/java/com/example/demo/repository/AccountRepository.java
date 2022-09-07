package com.example.demo.repository;

import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findMovieAccountByUsername(String username);

    Account findMovieAccountByEmail(String email);
    @Query(value = "select acc.* from account acc where acc.id=:id", nativeQuery = true)
    Account getAccount(@Param("id") Integer id);
    @Transactional
    @Modifying
    @Query(value = "delete from account a where a.id= ?", nativeQuery = true)
    void deleteAccount(Integer accountId);
}
