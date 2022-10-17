package com.example.demo.dto.map;

import com.example.demo.dto.MovieDto;
import com.example.demo.model.Movie;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public Movie movieDtoToMovie(MovieDto movieDto) {
        if ( movieDto == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setId( movieDto.getId() );
        movie.setName( movieDto.getName() );
        movie.setPoster( movieDto.getPoster() );
        movie.setSlug( movieDto.getSlug() );
        movie.setImage_showing( movieDto.getImage_showing() );
        movie.setDescription( movieDto.getDescription() );
        movie.setQuality( movieDto.getQuality() );
        movie.setMovieStatus( movieDto.getMovieStatus() );
        movie.setLinkTrailer( movieDto.getLinkTrailer() );
        movie.setLinkMovie( movieDto.getLinkMovie() );
        movie.setReleaseDate( movieDto.getReleaseDate() );
        movie.setMovieDuration( movieDto.getMovieDuration() );
        movie.setViewNumber( movieDto.getViewNumber() );
        movie.setTranslationStatus( movieDto.getTranslationStatus() );
        movie.setCountryCode( movieDto.getCountryCode() );
        movie.setMoviePrice( movieDto.getMoviePrice() );

        return movie;
    }

    @Override
    public MovieDto movieToMovieDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        movieDto.setId( movie.getId() );
        movieDto.setName( movie.getName() );
        movieDto.setImage_showing( movie.getImage_showing() );
        movieDto.setPoster( movie.getPoster() );
        movieDto.setSlug( movie.getSlug() );
        movieDto.setDescription( movie.getDescription() );
        movieDto.setQuality( movie.getQuality() );
        movieDto.setMovieStatus( movie.getMovieStatus() );
        movieDto.setLinkTrailer( movie.getLinkTrailer() );
        movieDto.setLinkMovie( movie.getLinkMovie() );
        movieDto.setReleaseDate( movie.getReleaseDate() );
        movieDto.setMovieDuration( movie.getMovieDuration() );
        movieDto.setViewNumber( movie.getViewNumber() );
        movieDto.setTranslationStatus( movie.getTranslationStatus() );
        movieDto.setCountryCode( movie.getCountryCode() );
        movieDto.setMoviePrice( movie.getMoviePrice() );

        return movieDto;
    }

    @Override
    public Movie updateMovieFromMovieDto(MovieDto movieDto, Movie movie) {
        if ( movieDto == null ) {
            return null;
        }

        if ( movieDto.getId() != null ) {
            movie.setId( movieDto.getId() );
        }
        if ( movieDto.getName() != null ) {
            movie.setName( movieDto.getName() );
        }
        if ( movieDto.getPoster() != null ) {
            movie.setPoster( movieDto.getPoster() );
        }
        if ( movieDto.getSlug() != null ) {
            movie.setSlug( movieDto.getSlug() );
        }
        if ( movieDto.getImage_showing() != null ) {
            movie.setImage_showing( movieDto.getImage_showing() );
        }
        if ( movieDto.getDescription() != null ) {
            movie.setDescription( movieDto.getDescription() );
        }
        if ( movieDto.getQuality() != null ) {
            movie.setQuality( movieDto.getQuality() );
        }
        if ( movieDto.getMovieStatus() != null ) {
            movie.setMovieStatus( movieDto.getMovieStatus() );
        }
        if ( movieDto.getLinkTrailer() != null ) {
            movie.setLinkTrailer( movieDto.getLinkTrailer() );
        }
        if ( movieDto.getLinkMovie() != null ) {
            movie.setLinkMovie( movieDto.getLinkMovie() );
        }
        if ( movieDto.getReleaseDate() != null ) {
            movie.setReleaseDate( movieDto.getReleaseDate() );
        }
        if ( movieDto.getMovieDuration() != null ) {
            movie.setMovieDuration( movieDto.getMovieDuration() );
        }
        if ( movieDto.getViewNumber() != null ) {
            movie.setViewNumber( movieDto.getViewNumber() );
        }
        if ( movieDto.getTranslationStatus() != null ) {
            movie.setTranslationStatus( movieDto.getTranslationStatus() );
        }
        if ( movieDto.getCountryCode() != null ) {
            movie.setCountryCode( movieDto.getCountryCode() );
        }
        if ( movieDto.getMoviePrice() != null ) {
            movie.setMoviePrice( movieDto.getMoviePrice() );
        }

        return movie;
    }
}
