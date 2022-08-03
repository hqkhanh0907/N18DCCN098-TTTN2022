package com.example.demo.dto.map;

import com.example.demo.dto.GenreOfMovieDto;
import com.example.demo.model.GenreOfMovie;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GenreOfMovieMapper {
    GenreOfMovie genreOfMovieDtoToGenreOfMovie(GenreOfMovieDto genreOfMovieDto);

    GenreOfMovieDto genreOfMovieToGenreOfMovieDto(GenreOfMovie genreOfMovie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GenreOfMovie updateGenreOfMovieFromGenreOfMovieDto(GenreOfMovieDto genreOfMovieDto, @MappingTarget GenreOfMovie genreOfMovie);
}
