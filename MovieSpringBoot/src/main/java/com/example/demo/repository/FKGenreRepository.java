package com.example.demo.repository;

import com.example.demo.model.GenreOfMovie;
import com.example.demo.model.Key.GenreOfMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FKGenreRepository extends JpaRepository<GenreOfMovie, GenreOfMovieKey> {
    @Transactional
    @Modifying
    @Query(value = "delete from genre_of_movie as a where a.movie_id = ? and a.genre_id = ?",
            nativeQuery = true)
    void deleteGenre(Integer movieId, Integer genreId);
    @Transactional
    @Modifying
    @Query(value = "insert into genre_of_movie(`movie_id`,`genre_id`) values (?,?)",
            nativeQuery = true)
    void saveGenre(Integer movieId, Integer genreId);
}
