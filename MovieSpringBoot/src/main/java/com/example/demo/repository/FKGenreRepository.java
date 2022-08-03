package com.example.demo.repository;

import com.example.demo.model.GenreOfMovie;
import com.example.demo.model.Key.GenreOfMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FKGenreRepository extends JpaRepository<GenreOfMovie, GenreOfMovieKey> {
}
