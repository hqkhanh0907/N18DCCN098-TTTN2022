package com.example.demo.repository;

import com.example.demo.model.BillingInformation;
import com.example.demo.model.Key.BillingInformationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface BillingInformationRepository extends JpaRepository<BillingInformation, BillingInformationKey> {
    @Transactional
    @Modifying
    @Query(value = "delete from billing_information bill where bill.account_id=? and bill.movie_id=?", nativeQuery = true)
    void deleteBill(Integer accountId, Integer movieId);
    @Transactional
    @Modifying
    @Query(value = "insert into billing_information(account_id, movie_id, promotion_id, status, price, date)" +
            "values (?,?,?,?,?,?)", nativeQuery = true)
    void saveNewBill(
            @Param("accountId") Integer accountId,
            @Param("movieId") Integer movieId,
            @Param("idPromo") Integer idPromo,
            @Param("status") Integer status,
            @Param("price") Double price,
            @Param("date") Date date);

    @Transactional
    @Modifying
    @Query(value = "update billing_information bill " +
            "set bill.status=:status, bill.date=:date, bill.promotion_id=:idPromo, bill.price=:price " +
            "where bill.account_id=:accountId and bill.movie_id=:movieId", nativeQuery = true)
    void updateBill(
            @Param("accountId") Integer accountId,
            @Param("movieId") Integer movieId,
            @Param("idPromo") Integer idPromo,
            @Param("status") Integer status,
            @Param("price") Double price,
            @Param("date") Date date);
    @Query(value = "select DISTINCT YEAR(bill.date) as year from billing_information bill order by year", nativeQuery = true)
    List<String> getYears();
    @Query(value = "select sum(bill.price) from billing_information bill where YEAR(bill.date) = :year", nativeQuery = true)
    Double getTotalPayByYear(@Param("year") String year);

    @Query(value = "select sum(bill.price) from billing_information bill", nativeQuery = true)
    Double sumAll();
    @Query(value = "select sum(bill.price) from billing_information bill where YEAR(bill.date) = :year and MONTH(bill.date) = :month", nativeQuery = true)
    Double sumByMonthAndYear(@Param("year") String year, @Param("month") String month);

    @Query(value = "select sum(bill.price) from billing_information bill where YEAR(bill.date) = :year", nativeQuery = true)
    Double sumByYear(@Param("year") String year);
}