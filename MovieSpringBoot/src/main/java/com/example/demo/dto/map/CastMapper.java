package com.example.demo.dto.map;

import com.example.demo.dto.CastDto;
import com.example.demo.model.Cast;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CastMapper {
    Cast castDtoToCast(CastDto castDto);

    CastDto castToCastDto(Cast cast);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cast updateCastFromCastDto(CastDto castDto, @MappingTarget Cast cast);

    @AfterMapping
    default void linkCastOfMovies(@MappingTarget Cast cast) {
        cast.getCastOfMovies().forEach(castOfMovie -> castOfMovie.setCast(cast));
    }
}
