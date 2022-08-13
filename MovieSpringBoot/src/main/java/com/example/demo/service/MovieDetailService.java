package com.example.demo.service;

import com.example.demo.dto.BillingInformationDto;
import com.example.demo.dto.CastDto;
import com.example.demo.dto.GenreDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.dto.MovieEvaluateDto;
import com.example.demo.dto.MovieRate;
import com.example.demo.dto.PromotionDto;
import com.example.demo.model.Movie;

import java.util.List;

public interface MovieDetailService {
    List<MovieDto> getAllMovie();

    MovieDto getMovieById(Integer movieId);
    MovieDto getMovieBySlug(String slug);


//    MovieDetailPage getAllMovieDetailPage(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    MovieDto addMovieDetail(Movie movieDTO) throws Exception;

    MovieDto editMovieDetail(Movie movieDTO) throws Exception;

    Movie deleteMovieDetail(Integer id) throws Exception;

    Movie getMovieDetailByName(String name);

    List<MovieRate> getListMovieRate() throws Exception;

    MovieRate getRateMovie(Integer id) throws Exception;

    List<GenreDto> getMovieGenres(Integer id);


    List<CastDto> getMovieCasts(Integer id);

    List<MovieDto> search(String searchQuery);

    List<MovieEvaluateDto> loadEvaluate(Integer movieId);

    List<MovieEvaluateDto> loadEvaluateInAcc(Integer accId);

    Movie saveEvaluate(MovieEvaluateDto movieEvaluateDTO);

    Boolean checkBillingByAccId(Integer accId, Integer movieId);

    Boolean addInfoBill(BillingInformationDto billingInformationDto);

    PromotionDto getPromotion(String promotionCode);
}
