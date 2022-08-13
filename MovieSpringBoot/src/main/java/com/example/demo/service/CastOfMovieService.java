package com.example.demo.service;


import com.example.demo.dto.CastOfMovieDto;
import com.example.demo.model.CastOfMovie;

import java.util.List;

public interface CastOfMovieService {
    //get all cast on a movie
    List<CastOfMovieDto> getCastByMovieId(Integer movieDetailId);

    //get all movie on a cast
    List<CastOfMovieDto> getMovieDetailByCastId(Integer castId);

    void deleteFkCastByCastId(Integer castId);

    Boolean deleteFkCastByMovieId(Integer movieDetailId);

    List<CastOfMovieDto> getAllFKCast();
    void removeFkCastExits(Integer movieId);

    void saveCastOfMovie(CastOfMovie castOfMovie);
}
