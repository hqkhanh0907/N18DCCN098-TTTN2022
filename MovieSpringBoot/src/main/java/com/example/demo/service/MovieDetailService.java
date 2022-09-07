package com.example.demo.service;

import com.example.demo.dto.*;
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
    List<DirectorDto> getMovieDirectors(Integer id);

    List<MovieDto> search(String searchQuery);

    List<MovieEvaluateDto> loadEvaluate(Integer movieId);

    List<MovieEvaluateDto> loadEvaluateInAcc(Integer accId);

    Boolean saveEvaluate(MovieEvaluateDto movieEvaluateDTO);

    Boolean checkBillingByAccId(Integer accId, Integer movieId);

    Boolean addInfoBill(BillingInformationDto billingInformationDto);

    PromotionDto getPromotion(String promotionCode);

    List<String> getAllCountriesCode();

    List<MovieDto> getMovieByGenreId(Integer genreId);

    List<MovieDto> getMovieByCountryCode(String code);

    List<MovieDto> getMovieByGenreIdAndCountryCode(Integer genreId, String code);

    List<MovieDto> getPopularMovies();
}
