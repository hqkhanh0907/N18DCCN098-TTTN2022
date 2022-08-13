package com.example.demo.dto.map;

import com.example.demo.dto.FavoriteMovieDto;
import com.example.demo.model.FavoriteMovie;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FavoriteMovieMapper {
    FavoriteMovie favoriteMovieDtoToFavoriteMovie(FavoriteMovieDto favoriteMovieDto);

    FavoriteMovieDto favoriteMovieToFavoriteMovieDto(FavoriteMovie favoriteMovie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FavoriteMovie updateFavoriteMovieFromFavoriteMovieDto(FavoriteMovieDto favoriteMovieDto, @MappingTarget FavoriteMovie favoriteMovie);
}
