package com.example.demo.service;

import com.example.demo.dto.GenreDto;

import java.util.List;
import java.util.Map;

public interface MovieGenreService {
    List<GenreDto> getAllMovieGen();

    GenreDto getMovieGenreById(Integer id);

    Boolean deleteMovieGenreById(Integer id);

    GenreDto createMovieGenre(GenreDto movieGenreDTO);

    Boolean editMovieGenre(GenreDto genreDto);

    Integer getNumGenre();

    Map<String, List<?>> getGenreBar();
}
