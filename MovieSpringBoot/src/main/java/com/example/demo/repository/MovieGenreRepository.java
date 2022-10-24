package com.example.demo.repository;

import com.example.demo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovieGenreRepository extends JpaRepository<Genre, Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from genre gr where gr.id=?", nativeQuery = true)
    void deleteGenre(Integer id);

    @Query(value = "select count(*) from genre", nativeQuery = true)
    Integer getNumGenre();

    @Query(value = "select g.name from genre g", nativeQuery = true)
    List<String> getGenreName();

    @Query(value = "select count(*) from genre g left join genre_of_movie gom on g.id = gom.genre_id where gom.genre_id is not null  and gom.movie_id is not null and g.name = :name", nativeQuery = true)
    Integer getNumGenreName(@Param("name") String name);
}
