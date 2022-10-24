package com.example.demo.repository;

import com.example.demo.model.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCastRepository extends JpaRepository<Cast, Integer> {
    @Query(value = "select count(*) from cast", nativeQuery = true)
    Integer getNumCast();
}
