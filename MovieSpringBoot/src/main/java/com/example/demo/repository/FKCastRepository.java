package com.example.demo.repository;

import com.example.demo.model.CastOfMovie;
import com.example.demo.model.Key.CastOfMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FKCastRepository extends JpaRepository<CastOfMovie, CastOfMovieKey> {
}
