package com.example.demo.repository;

import com.example.demo.model.Key.MovieEvaluateKey;
import com.example.demo.model.MovieEvaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface MovieEvaluateRepository extends JpaRepository<MovieEvaluate, MovieEvaluateKey> {
    @Transactional
    @Modifying
    @Query(value = "delete from movie_evaluate hs where hs.account_id=? and hs.movie_id=?", nativeQuery = true)
    void deleteEvaluate(Integer accountId, Integer movieId);

    @Transactional
    @Modifying
    @Query(value = "insert into " +
            "movie_evaluate(`account_id`,`movie_id`,`rate`,`content`,`date`,`status`) " +
            " values (?,?,?,?,?,?)", nativeQuery = true)
    void saveEvaluate(Integer userId, Integer movieId, Integer rate, String content, Date date, Integer status);

    @Transactional
    @Modifying
    @Query(value = "update movie_evaluate eva" +
            " set" +
            " eva.account_id=:userId," +
            " eva.movie_id=:movieId," +
            " eva.rate=:rate," +
            " eva.content=:content," +
            " eva.date=:date," +
            " eva.status=:status" +
            " where eva.account_id=:userId and eva.movie_id=:movieId", nativeQuery = true)
    void updateEvaluate(
            @Param("userId") Integer userId,
            @Param("movieId") Integer movieId,
            @Param("rate") Integer rate,
            @Param("content") String content,
            @Param("date") Date date,
            @Param("status") Integer status);
}
