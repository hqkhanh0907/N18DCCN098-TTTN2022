package com.example.demo.repository;

import com.example.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieDetailRepository extends JpaRepository<Movie, Integer> {
    Movie findMovieDetailByName(String name);
    Movie findMovieDetailBySlug(String slug);
    List<Movie> findByNameLike(String name);

}
