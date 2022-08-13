package com.example.demo.service;

import com.example.demo.dto.DirectorDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.model.DirectorOfMovie;

import java.util.List;

public interface FKDirectorService {

    //get all cast on a movie
    List<DirectorDto> getDirectorByMovieId(Integer movieDetailId);

    //get all movie on a cast
    List<MovieDto> getMovieDetailByDirectorId(Integer directorId);

    void deleteFkDirectorByDirectorId(Integer directorId);

    void deleteFkDirectorByMovieId(Integer movieDetailId);

    void removieDirectorExits(Integer movieId);

    void saveDirectorOfMovie(DirectorOfMovie director);
}
