package com.example.demo.repository;

import com.example.demo.model.BillingInformation;
import com.example.demo.model.Key.BillingInformationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BillingInformationRepository extends JpaRepository<BillingInformation, BillingInformationKey> {
    @Transactional
    @Modifying
    @Query(value = "delete from billing_information bill where bill.account_id=? and bill.movie_id=?", nativeQuery = true)
    void deleteBill(Integer accountId, Integer movieId);
}