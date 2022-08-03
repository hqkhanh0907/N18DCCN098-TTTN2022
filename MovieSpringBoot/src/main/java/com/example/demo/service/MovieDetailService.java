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

    MovieDto getMovieById(int movieId);
    MovieDto getMovieBySlug(String slug);


//    MovieDetailPage getAllMovieDetailPage(int pageNo, int pageSize, String sortBy, String sortDir);

    MovieDto addMovieDetail(Movie movieDTO) throws Exception;

    MovieDto editMovieDetail(Movie movieDTO) throws Exception;

    Movie deleteMovieDetail(int id) throws Exception;

    Movie getMovieDetailByName(String name);

    List<MovieRate> getListMovieRate() throws Exception;

    MovieRate getRateMovie(int id) throws Exception;

    List<GenreDto> getMovieGenres(int id);


    List<CastDto> getMovieCasts(int id);

    List<MovieDto> search(String searchQuery);

    List<MovieEvaluateDto> loadEvaluate(int movieId);

    List<MovieEvaluateDto> loadEvaluateInAcc(int accId);

    Movie saveEvaluate(MovieEvaluateDto movieEvaluateDTO);

    Boolean checkBillingByAccId(int accId, int movieId);

    Boolean addInfoBill(BillingInformationDto billingInformationDto);

    PromotionDto getPromotion(String promotionCode);
}
