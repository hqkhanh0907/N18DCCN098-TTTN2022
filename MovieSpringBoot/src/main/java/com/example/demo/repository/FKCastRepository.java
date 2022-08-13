package com.example.demo.repository;

import com.example.demo.model.CastOfMovie;
import com.example.demo.model.Key.CastOfMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FKCastRepository extends JpaRepository<CastOfMovie, CastOfMovieKey> {
    @Transactional
    @Modifying
    @Query(value = "delete from cast_of_movie as a where a.movie_id = ? and a.cast_id = ?",
            nativeQuery = true)
    void deleteCastOfMovie(Integer movieId, Integer castId);
    @Transactional
    @Modifying
    @Query(value = "insert into cast_of_movie(`movie_id`,`cast_id`, `cast_character`) values (?,?,?)",
            nativeQuery = true)
    void saveCastOfMovie(Integer movieId, Integer castId, String character);
}
