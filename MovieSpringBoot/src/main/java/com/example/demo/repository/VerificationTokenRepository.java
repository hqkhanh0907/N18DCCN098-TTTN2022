package com.example.demo.repository;

import com.example.demo.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByTokenContent(String tokenContent);
    @Transactional
    @Modifying
    @Query(value = "delete from token tk where tk.account_id=?", nativeQuery = true)
    void deleteAccountToken(Integer accountId);
}
