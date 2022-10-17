package com.example.demo.dto.map;

import com.example.demo.dto.GenreOfMovieDto;
import com.example.demo.dto.key.GenreOfMovieKeyDto;
import com.example.demo.model.GenreOfMovie;
import com.example.demo.model.Key.GenreOfMovieKey;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class GenreOfMovieMapperImpl implements GenreOfMovieMapper {

    @Override
    public GenreOfMovie genreOfMovieDtoToGenreOfMovie(GenreOfMovieDto genreOfMovieDto) {
        if ( genreOfMovieDto == null ) {
            return null;
        }

        GenreOfMovie genreOfMovie = new GenreOfMovie();

        genreOfMovie.setId( genreOfMovieKeyDtoToGenreOfMovieKey( genreOfMovieDto.getId() ) );

        return genreOfMovie;
    }

    @Override
    public GenreOfMovieDto genreOfMovieToGenreOfMovieDto(GenreOfMovie genreOfMovie) {
        if ( genreOfMovie == null ) {
            return null;
        }

        GenreOfMovieDto genreOfMovieDto = new GenreOfMovieDto();

        genreOfMovieDto.setId( genreOfMovieKeyToGenreOfMovieKeyDto( genreOfMovie.getId() ) );

        return genreOfMovieDto;
    }

    @Override
    public GenreOfMovie updateGenreOfMovieFromGenreOfMovieDto(GenreOfMovieDto genreOfMovieDto, GenreOfMovie genreOfMovie) {
        if ( genreOfMovieDto == null ) {
            return null;
        }

        if ( genreOfMovieDto.getId() != null ) {
            if ( genreOfMovie.getId() == null ) {
                genreOfMovie.setId( new GenreOfMovieKey() );
            }
            genreOfMovieKeyDtoToGenreOfMovieKey1( genreOfMovieDto.getId(), genreOfMovie.getId() );
        }

        return genreOfMovie;
    }

    protected GenreOfMovieKey genreOfMovieKeyDtoToGenreOfMovieKey(GenreOfMovieKeyDto genreOfMovieKeyDto) {
        if ( genreOfMovieKeyDto == null ) {
            return null;
        }

        GenreOfMovieKey genreOfMovieKey = new GenreOfMovieKey();

        genreOfMovieKey.setGenreId( genreOfMovieKeyDto.getGenreId() );
        genreOfMovieKey.setMovieId( genreOfMovieKeyDto.getMovieId() );

        return genreOfMovieKey;
    }

    protected GenreOfMovieKeyDto genreOfMovieKeyToGenreOfMovieKeyDto(GenreOfMovieKey genreOfMovieKey) {
        if ( genreOfMovieKey == null ) {
            return null;
        }

        GenreOfMovieKeyDto genreOfMovieKeyDto = new GenreOfMovieKeyDto();

        genreOfMovieKeyDto.setGenreId( genreOfMovieKey.getGenreId() );
        genreOfMovieKeyDto.setMovieId( genreOfMovieKey.getMovieId() );

        return genreOfMovieKeyDto;
    }

    protected void genreOfMovieKeyDtoToGenreOfMovieKey1(GenreOfMovieKeyDto genreOfMovieKeyDto, GenreOfMovieKey mappingTarget) {
        if ( genreOfMovieKeyDto == null ) {
            return;
        }

        if ( genreOfMovieKeyDto.getGenreId() != null ) {
            mappingTarget.setGenreId( genreOfMovieKeyDto.getGenreId() );
        }
        if ( genreOfMovieKeyDto.getMovieId() != null ) {
            mappingTarget.setMovieId( genreOfMovieKeyDto.getMovieId() );
        }
    }
}
