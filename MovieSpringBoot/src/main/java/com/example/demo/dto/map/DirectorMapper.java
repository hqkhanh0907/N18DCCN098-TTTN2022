package com.example.demo.dto.map;

import com.example.demo.dto.DirectorDto;
import com.example.demo.model.Director;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DirectorMapper {
    Director directorDtoToDirector(DirectorDto directorDto);

    DirectorDto directorToDirectorDto(Director director);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Director updateDirectorFromDirectorDto(DirectorDto directorDto, @MappingTarget Director director);
}
