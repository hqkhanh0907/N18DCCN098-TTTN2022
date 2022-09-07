package com.example.demo.repository;

import com.example.demo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MovieGenreRepository extends JpaRepository<Genre, Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from genre gr where gr.id=?", nativeQuery = true)
    void deleteGenre(Integer id);
}
