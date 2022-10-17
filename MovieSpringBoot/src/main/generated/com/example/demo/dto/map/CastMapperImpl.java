package com.example.demo.dto.map;

import com.example.demo.dto.CastDto;
import com.example.demo.dto.CastOfMovieDto;
import com.example.demo.dto.key.CastOfMovieKeyDto;
import com.example.demo.model.Cast;
import com.example.demo.model.CastOfMovie;
import com.example.demo.model.Key.CastOfMovieKey;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class CastMapperImpl implements CastMapper {

    @Override
    public Cast castDtoToCast(CastDto castDto) {
        if ( castDto == null ) {
            return null;
        }

        Cast cast = new Cast();

        cast.setId( castDto.getId() );
        cast.setAvatar( castDto.getAvatar() );
        cast.setName( castDto.getName() );
        cast.setStory( castDto.getStory() );
        cast.setBirthday( castDto.getBirthday() );
        cast.setCastOfMovies( castOfMovieDtoListToCastOfMovieList( castDto.getCastOfMovies() ) );

        linkCastOfMovies( cast );

        return cast;
    }

    @Override
    public CastDto castToCastDto(Cast cast) {
        if ( cast == null ) {
            return null;
        }

        CastDto castDto = new CastDto();

        castDto.setId( cast.getId() );
        castDto.setAvatar( cast.getAvatar() );
        castDto.setName( cast.getName() );
        castDto.setStory( cast.getStory() );
        castDto.setBirthday( cast.getBirthday() );
        castDto.setCastOfMovies( castOfMovieListToCastOfMovieDtoList( cast.getCastOfMovies() ) );

        return castDto;
    }

    @Override
    public Cast updateCastFromCastDto(CastDto castDto, Cast cast) {
        if ( castDto == null ) {
            return null;
        }

        if ( castDto.getId() != null ) {
            cast.setId( castDto.getId() );
        }
        if ( castDto.getAvatar() != null ) {
            cast.setAvatar( castDto.getAvatar() );
        }
        if ( castDto.getName() != null ) {
            cast.setName( castDto.getName() );
        }
        if ( castDto.getStory() != null ) {
            cast.setStory( castDto.getStory() );
        }
        if ( castDto.getBirthday() != null ) {
            cast.setBirthday( castDto.getBirthday() );
        }
        if ( cast.getCastOfMovies() != null ) {
            List<CastOfMovie> list = castOfMovieDtoListToCastOfMovieList( castDto.getCastOfMovies() );
            if ( list != null ) {
                cast.getCastOfMovies().clear();
                cast.getCastOfMovies().addAll( list );
            }
        }
        else {
            List<CastOfMovie> list = castOfMovieDtoListToCastOfMovieList( castDto.getCastOfMovies() );
            if ( list != null ) {
                cast.setCastOfMovies( list );
            }
        }

        linkCastOfMovies( cast );

        return cast;
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

    protected CastOfMovie castOfMovieDtoToCastOfMovie(CastOfMovieDto castOfMovieDto) {
        if ( castOfMovieDto == null ) {
            return null;
        }

        CastOfMovie castOfMovie = new CastOfMovie();

        castOfMovie.setId( castOfMovieKeyDtoToCastOfMovieKey( castOfMovieDto.getId() ) );
        castOfMovie.setCastCharacter( castOfMovieDto.getCastCharacter() );

        return castOfMovie;
    }

    protected List<CastOfMovie> castOfMovieDtoListToCastOfMovieList(List<CastOfMovieDto> list) {
        if ( list == null ) {
            return null;
        }

        List<CastOfMovie> list1 = new ArrayList<CastOfMovie>( list.size() );
        for ( CastOfMovieDto castOfMovieDto : list ) {
            list1.add( castOfMovieDtoToCastOfMovie( castOfMovieDto ) );
        }

        return list1;
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

    protected CastOfMovieDto castOfMovieToCastOfMovieDto(CastOfMovie castOfMovie) {
        if ( castOfMovie == null ) {
            return null;
        }

        CastOfMovieDto castOfMovieDto = new CastOfMovieDto();

        castOfMovieDto.setId( castOfMovieKeyToCastOfMovieKeyDto( castOfMovie.getId() ) );
        castOfMovieDto.setCastCharacter( castOfMovie.getCastCharacter() );

        return castOfMovieDto;
    }

    protected List<CastOfMovieDto> castOfMovieListToCastOfMovieDtoList(List<CastOfMovie> list) {
        if ( list == null ) {
            return null;
        }

        List<CastOfMovieDto> list1 = new ArrayList<CastOfMovieDto>( list.size() );
        for ( CastOfMovie castOfMovie : list ) {
            list1.add( castOfMovieToCastOfMovieDto( castOfMovie ) );
        }

        return list1;
    }
}
