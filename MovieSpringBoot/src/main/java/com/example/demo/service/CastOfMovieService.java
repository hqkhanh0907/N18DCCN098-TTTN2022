package com.example.demo.service;


import com.example.demo.dto.CastDto;
import com.example.demo.dto.CastOfMovieDto;
import com.example.demo.dto.MovieDto;

import java.util.List;

public interface CastOfMovieService {
    //get all cast on a movie
    List<CastDto> getCastByMovieId(Integer movieDetailId);

    //get all movie on a cast
    List<MovieDto> getMovieDetailByCastId(Integer castId);

    void deleteFkCastByCastId(int castId);

    void deleteFkCastByMovieId(int movieDetailId);

    List<CastOfMovieDto> getAllFKCast();
    void removeFkCastExits(Integer movieId);
}
