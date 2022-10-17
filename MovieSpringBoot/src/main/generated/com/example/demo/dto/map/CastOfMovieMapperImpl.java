package com.example.demo.dto.map;

import com.example.demo.dto.CastOfMovieDto;
import com.example.demo.dto.key.CastOfMovieKeyDto;
import com.example.demo.model.CastOfMovie;
import com.example.demo.model.Key.CastOfMovieKey;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class CastOfMovieMapperImpl implements CastOfMovieMapper {

    @Override
    public CastOfMovie castOfMovieDtoToCastOfMovie(CastOfMovieDto castOfMovieDto) {
        if ( castOfMovieDto == null ) {
            return null;
        }

        CastOfMovie castOfMovie = new CastOfMovie();

        castOfMovie.setId( castOfMovieKeyDtoToCastOfMovieKey( castOfMovieDto.getId() ) );
        castOfMovie.setCastCharacter( castOfMovieDto.getCastCharacter() );

        return castOfMovie;
    }

    @Override
    public CastOfMovieDto castOfMovieToCastOfMovieDto(CastOfMovie castOfMovie) {
        if ( castOfMovie == null ) {
            return null;
        }

        CastOfMovieDto castOfMovieDto = new CastOfMovieDto();

        castOfMovieDto.setId( castOfMovieKeyToCastOfMovieKeyDto( castOfMovie.getId() ) );
        castOfMovieDto.setCastCharacter( castOfMovie.getCastCharacter() );

        return castOfMovieDto;
    }

    @Override
    public CastOfMovie updateCastOfMovieFromCastOfMovieDto(CastOfMovieDto castOfMovieDto, CastOfMovie castOfMovie) {
        if ( castOfMovieDto == null ) {
            return null;
        }

        if ( castOfMovieDto.getId() != null ) {
            if ( castOfMovie.getId() == null ) {
                castOfMovie.setId( new CastOfMovieKey() );
            }
            castOfMovieKeyDtoToCastOfMovieKey1( castOfMovieDto.getId(), castOfMovie.getId() );
        }
        if ( castOfMovieDto.getCastCharacter() != null ) {
            castOfMovie.setCastCharacter( castOfMovieDto.getCastCharacter() );
        }

        return castOfMovie;
    }

    protected CastOfMovieKey castOfMovieKeyDtoToCastOfMovieKey(CastOfMovieKeyDto castOfMovieKeyDto) {
        if ( castOfMovieKeyDto == null ) {
            return null;
        }

        CastOfMovieKey castOfMovieKey = new CastOfMovieKey();

        castOfMovieKey.setCastId( castOfMovieKeyDto.getCastId() );
        castOfMovieKey.setMovieId( castOfMovieKeyDto.getMovieId() );

        return castOfMovieKey;
    }

    protected CastOfMovieKeyDto castOfMovieKeyToCastOfMovieKeyDto(CastOfMovieKey castOfMovieKey) {
        if ( castOfMovieKey == null ) {
            return null;
        }

        CastOfMovieKeyDto castOfMovieKeyDto = new CastOfMovieKeyDto();

        castOfMovieKeyDto.setCastId( castOfMovieKey.getCastId() );
        castOfMovieKeyDto.setMovieId( castOfMovieKey.getMovieId() );

        return castOfMovieKeyDto;
    }

    protected void castOfMovieKeyDtoToCastOfMovieKey1(CastOfMovieKeyDto castOfMovieKeyDto, CastOfMovieKey mappingTarget) {
        if ( castOfMovieKeyDto == null ) {
            return;
        }

        if ( castOfMovieKeyDto.getCastId() != null ) {
            mappingTarget.setCastId( castOfMovieKeyDto.getCastId() );
        }
        if ( castOfMovieKeyDto.getMovieId() != null ) {
            mappingTarget.setMovieId( castOfMovieKeyDto.getMovieId() );
        }
    }
}
