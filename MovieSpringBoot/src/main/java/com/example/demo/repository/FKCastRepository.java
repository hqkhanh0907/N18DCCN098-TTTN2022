package com.example.demo.repository;

import com.example.demo.model.CastOfMovie;
import com.example.demo.model.Key.CastOfMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FKCastRepository extends JpaRepository<CastOfMovie, CastOfMovieKey> {
}
