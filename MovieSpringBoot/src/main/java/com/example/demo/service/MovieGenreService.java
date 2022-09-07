package com.example.demo.service;

import com.example.demo.dto.GenreDto;

import java.util.List;

public interface MovieGenreService {
    List<GenreDto> getAllMovieGen();

    GenreDto getMovieGenreById(Integer id);

    Boolean deleteMovieGenreById(Integer id);

    GenreDto createMovieGenre(GenreDto movieGenreDTO);

    Boolean editMovieGenre(GenreDto genreDto);
}
