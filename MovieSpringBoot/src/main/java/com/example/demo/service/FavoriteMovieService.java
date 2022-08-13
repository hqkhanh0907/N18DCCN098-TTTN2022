package com.example.demo.service;

import com.example.demo.dto.key.FavoriteMovieKeyDto;

public interface FavoriteMovieService {
    Boolean unFollow(FavoriteMovieKeyDto favoriteMovieKeyDto);
}
