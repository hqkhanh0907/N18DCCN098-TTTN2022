package com.example.demo.service.implement;

import com.example.demo.model.MovieEvaluate;
import com.example.demo.repository.MovieEvaluateRepository;
import com.example.demo.service.MovieEvaluateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieEvaluateServiceImpl implements MovieEvaluateService {
    private final MovieEvaluateRepository movieEvaluateRepository;


    @Override
    public List<MovieEvaluate> getMovieEvaluates() {
        return movieEvaluateRepository.findAll();
    }

    @Override
    public void deleteMovieEvaluateByMovieId(Integer movieId) {
        List<MovieEvaluate> movieEvaluates = movieEvaluateRepository.findAll();
        movieEvaluates.forEach(movieEvaluate -> {
            if (movieEvaluate.getId().getMovieId() == movieId) {
                movieEvaluateRepository.delete(movieEvaluate);
            }
        });
    }

    @Override
    public void deleteMovieEvaluateByUserId(Integer userId) {
        List<MovieEvaluate> movieEvaluates = movieEvaluateRepository.findAll();
        movieEvaluates.forEach(movieEvaluate -> {
            if (movieEvaluate.getId().getUserId() == userId) {
                movieEvaluateRepository.delete(movieEvaluate);
            }
        });
    }

    @Override
    public void editEvaluate(MovieEvaluate movieEvaluate) {
        movieEvaluateRepository.save(movieEvaluate);
    }
}
