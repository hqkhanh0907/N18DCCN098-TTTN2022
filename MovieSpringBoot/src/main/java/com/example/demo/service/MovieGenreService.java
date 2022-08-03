package com.example.demo.service;

import com.example.demo.dto.GenreDto;

import java.util.List;

public interface MovieGenreService {
    List<GenreDto> getAllMovieGen();

    GenreDto getMovieGenreById(int id);

    String deleteMovieGenreById(int id);

    GenreDto createMovieGenre(GenreDto movieGenreDTO);

    String editMovieGenre(GenreDto movieGenreDTO);
}
