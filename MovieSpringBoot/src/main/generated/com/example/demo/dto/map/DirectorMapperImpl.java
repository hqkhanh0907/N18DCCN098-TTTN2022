package com.example.demo.dto.map;

import com.example.demo.dto.DirectorDto;
import com.example.demo.model.Director;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class DirectorMapperImpl implements DirectorMapper {

    @Override
    public Director directorDtoToDirector(DirectorDto directorDto) {
        if ( directorDto == null ) {
            return null;
        }

        Director director = new Director();

        director.setId( directorDto.getId() );
        director.setAvatar( directorDto.getAvatar() );
        director.setName( directorDto.getName() );
        director.setStory( directorDto.getStory() );
        director.setBirthday( directorDto.getBirthday() );

        return director;
    }

    @Override
    public DirectorDto directorToDirectorDto(Director director) {
        if ( director == null ) {
            return null;
        }

        DirectorDto directorDto = new DirectorDto();

        directorDto.setId( director.getId() );
        directorDto.setAvatar( director.getAvatar() );
        directorDto.setName( director.getName() );
        directorDto.setStory( director.getStory() );
        directorDto.setBirthday( director.getBirthday() );

        return directorDto;
    }

    @Override
    public Director updateDirectorFromDirectorDto(DirectorDto directorDto, Director director) {
        if ( directorDto == null ) {
            return null;
        }

        if ( directorDto.getId() != null ) {
            director.setId( directorDto.getId() );
        }
        if ( directorDto.getAvatar() != null ) {
            director.setAvatar( directorDto.getAvatar() );
        }
        if ( directorDto.getName() != null ) {
            director.setName( directorDto.getName() );
        }
        if ( directorDto.getStory() != null ) {
            director.setStory( directorDto.getStory() );
        }
        if ( directorDto.getBirthday() != null ) {
            director.setBirthday( directorDto.getBirthday() );
        }

        return director;
    }
}
