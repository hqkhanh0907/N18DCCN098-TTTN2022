package com.example.demo.repository;

import com.example.demo.model.FavoriteMovie;
import com.example.demo.model.Key.FavoriteMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, FavoriteMovieKey> {
}