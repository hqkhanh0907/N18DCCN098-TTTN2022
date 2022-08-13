package com.example.demo.service;

import com.example.demo.model.MovieEvaluate;

import java.util.List;

public interface MovieEvaluateService {

    List<MovieEvaluate> getMovieEvaluates();

    void deleteMovieEvaluateByMovieId(Integer movieId);

    void deleteMovieEvaluateByUserId(Integer userId);

    void editEvaluate(MovieEvaluate movieEvaluate);
}
