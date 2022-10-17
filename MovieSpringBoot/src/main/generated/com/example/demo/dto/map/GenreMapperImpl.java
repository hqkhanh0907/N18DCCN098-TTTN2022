package com.example.demo.dto.map;

import com.example.demo.dto.GenreDto;
import com.example.demo.model.Genre;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public Genre genreDtoToGenre(GenreDto genreDto) {
        if ( genreDto == null ) {
            return null;
        }

        Genre genre = new Genre();

        genre.setId( genreDto.getId() );
        genre.setName( genreDto.getName() );

        return genre;
    }

    @Override
    public GenreDto genreToGenreDto(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreDto genreDto = new GenreDto();

        genreDto.setId( genre.getId() );
        genreDto.setName( genre.getName() );

        return genreDto;
    }

    @Override
    public Genre updateGenreFromGenreDto(GenreDto genreDto, Genre genre) {
        if ( genreDto == null ) {
            return null;
        }

        if ( genreDto.getId() != null ) {
            genre.setId( genreDto.getId() );
        }
        if ( genreDto.getName() != null ) {
            genre.setName( genreDto.getName() );
        }

        return genre;
    }
}
