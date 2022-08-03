package com.example.demo.service;

import com.example.demo.dto.DirectorDto;
import com.example.demo.dto.MovieDto;

import java.util.List;

public interface FKDirectorService {

    //get all cast on a movie
    List<DirectorDto> getDirectorByMovieId(int movieDetailId);

    //get all movie on a cast
    List<MovieDto> getMovieDetailByDirectorId(int directorId);

    void deleteFkDirectorByDirectorId(int directorId);

    void deleteFkDirectorByMovieId(int movieDetailId);

    void removieDirectorExits(Integer movieId);
}
