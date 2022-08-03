package com.example.demo.repository;

import com.example.demo.model.Cast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCastRepository extends JpaRepository<Cast, Integer> {
}
