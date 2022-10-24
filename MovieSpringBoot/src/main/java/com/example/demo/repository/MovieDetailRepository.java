package com.example.demo.repository;

import com.example.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDetailRepository extends JpaRepository<Movie, Integer> {
    Movie findMovieDetailByName(String name);

    Movie findMovieDetailBySlug(String slug);

    @Query(value = "select * from movie as m where m.name like :search", nativeQuery = true)
    List<Movie> findMovieByName(@Param("search") String searchName);
    @Query(value = "select count(*) from movie", nativeQuery = true)
    Integer getNumMovies();

    @Query(value = "select count(*) from movie m where m.movie_status = 1", nativeQuery = true)
    Integer getPremiered();

    @Query(value = "select count(*) from movie m where m.movie_status = 0", nativeQuery = true)
    Integer getComing();
}
