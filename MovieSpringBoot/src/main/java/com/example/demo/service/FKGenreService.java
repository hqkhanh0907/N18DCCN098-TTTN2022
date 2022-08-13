package com.example.demo.service;

import com.example.demo.dto.GenreDto;
import com.example.demo.dto.GenreOfMovieDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.model.GenreOfMovie;

import java.util.List;

public interface FKGenreService {
    void saveGenreOfMovie(GenreOfMovie genreOfMovieDto);
    List<GenreOfMovieDto> getAllFKGenre();
    //get all genre on a movie
    List<GenreDto> getGenreOnMovie(Integer movieId);

    //get all movie on a genre
    List<MovieDto> getMovieOnGenre(Integer genreId);
    void deleteByGenreId(Integer genreId);

    void deleteByMovieId(Integer movieId);

    void removeGenreExits(Integer movieId);
}
