package com.example.demo.repository;

import com.example.demo.model.DirectorOfMovie;
import com.example.demo.model.Key.DirectorOfMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FKDirectorRepository extends JpaRepository<DirectorOfMovie, DirectorOfMovieKey> {
}
