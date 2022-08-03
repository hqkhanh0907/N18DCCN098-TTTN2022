package com.example.demo.dto.map;

import com.example.demo.dto.CastOfMovieDto;
import com.example.demo.model.CastOfMovie;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CastOfMovieMapper {
    CastOfMovie castOfMovieDtoToCastOfMovie(CastOfMovieDto castOfMovieDto);

    CastOfMovieDto castOfMovieToCastOfMovieDto(CastOfMovie castOfMovie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CastOfMovie updateCastOfMovieFromCastOfMovieDto(CastOfMovieDto castOfMovieDto, @MappingTarget CastOfMovie castOfMovie);
}
