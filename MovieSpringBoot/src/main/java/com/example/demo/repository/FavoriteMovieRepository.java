package com.example.demo.repository;

import com.example.demo.model.FavoriteMovie;
import com.example.demo.model.Key.FavoriteMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, FavoriteMovieKey> {
    @Transactional
    @Modifying
    @Query(value = "delete from favorite_movie fav where fav.account_id=? and fav.movie_id=?", nativeQuery = true)
    void deleteFavorte(Integer accountId, Integer movieId);
}