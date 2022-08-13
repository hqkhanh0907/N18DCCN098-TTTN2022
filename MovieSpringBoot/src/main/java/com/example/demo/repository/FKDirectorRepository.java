package com.example.demo.repository;

import com.example.demo.model.DirectorOfMovie;
import com.example.demo.model.Key.DirectorOfMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FKDirectorRepository extends JpaRepository<DirectorOfMovie, DirectorOfMovieKey> {
    @Transactional
    @Modifying
    @Query(value = "delete from director_of_movie as a where a.movie_id = ? and a.dricetor_id = ?",
            nativeQuery = true)
    void deleteDirector(Integer movieId, Integer genreId);
    @Transactional
    @Modifying
    @Query(value = "insert into director_of_movie(`movie_id`,`dricetor_id`) values (?,?)",
            nativeQuery = true)
    void saveDirector(Integer movieId, Integer directorId);
}
