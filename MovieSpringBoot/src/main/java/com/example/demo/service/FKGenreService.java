package com.example.demo.service;

import com.example.demo.dto.GenreDto;
import com.example.demo.dto.GenreOfMovieDto;
import com.example.demo.dto.MovieDto;

import java.util.List;

public interface FKGenreService {
    List<GenreOfMovieDto> getAllFKGenre();
    //get all genre on a movie
    List<GenreDto> getGenreOnMovie(int movieId);

    //get all movie on a genre
    List<MovieDto> getMovieOnGenre(int genreId);
    void deleteByGenreId(int genreId);

    void deleteByMovieId(int movieId);

    void removeGenreExits(Integer movieId);
}
