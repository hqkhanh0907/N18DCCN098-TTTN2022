package com.example.demo.dto.map;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.AccountHistoryDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.dto.MovieEvaluateDto;
import com.example.demo.dto.key.AccountHistoryKeyDto;
import com.example.demo.dto.key.MovieEvaluateKeyDto;
import com.example.demo.model.Account;
import com.example.demo.model.AccountHistory;
import com.example.demo.model.Key.AccountHistoryKey;
import com.example.demo.model.Key.MovieEvaluateKey;
import com.example.demo.model.Movie;
import com.example.demo.model.MovieEvaluate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class MovieEvaluateMapperImpl implements MovieEvaluateMapper {

    @Override
    public MovieEvaluate movieEvaluateDtoToMovieEvaluate(MovieEvaluateDto movieEvaluateDto) {
        if ( movieEvaluateDto == null ) {
            return null;
        }

        MovieEvaluate movieEvaluate = new MovieEvaluate();

        movieEvaluate.setId( movieEvaluateKeyDtoToMovieEvaluateKey( movieEvaluateDto.getId() ) );
        movieEvaluate.setRate( movieEvaluateDto.getRate() );
        movieEvaluate.setContent( movieEvaluateDto.getContent() );
        movieEvaluate.setDate( movieEvaluateDto.getDate() );
        movieEvaluate.setStatus( movieEvaluateDto.getStatus() );

        return movieEvaluate;
    }

    @Override
    public MovieEvaluateDto movieEvaluateToMovieEvaluateDto(MovieEvaluate movieEvaluate) {
        if ( movieEvaluate == null ) {
            return null;
        }

        MovieEvaluateDto movieEvaluateDto = new MovieEvaluateDto();

        movieEvaluateDto.setId( movieEvaluateKeyToMovieEvaluateKeyDto( movieEvaluate.getId() ) );
        movieEvaluateDto.setRate( movieEvaluate.getRate() );
        movieEvaluateDto.setContent( movieEvaluate.getContent() );
        movieEvaluateDto.setDate( movieEvaluate.getDate() );
        movieEvaluateDto.setStatus( movieEvaluate.getStatus() );

        return movieEvaluateDto;
    }

    @Override
    public MovieEvaluate updateMovieEvaluateFromMovieEvaluateDto(MovieEvaluateDto movieEvaluateDto, MovieEvaluate movieEvaluate) {
        if ( movieEvaluateDto == null ) {
            return null;
        }

        if ( movieEvaluateDto.getId() != null ) {
            if ( movieEvaluate.getId() == null ) {
                movieEvaluate.setId( new MovieEvaluateKey() );
            }
            movieEvaluateKeyDtoToMovieEvaluateKey1( movieEvaluateDto.getId(), movieEvaluate.getId() );
        }
        if ( movieEvaluateDto.getRate() != null ) {
            movieEvaluate.setRate( movieEvaluateDto.getRate() );
        }
        if ( movieEvaluateDto.getContent() != null ) {
            movieEvaluate.setContent( movieEvaluateDto.getContent() );
        }
        if ( movieEvaluateDto.getDate() != null ) {
            movieEvaluate.setDate( movieEvaluateDto.getDate() );
        }
        if ( movieEvaluateDto.getStatus() != null ) {
            movieEvaluate.setStatus( movieEvaluateDto.getStatus() );
        }

        return movieEvaluate;
    }

    @Override
    public AccountHistory updateAccountHistoryFromAccountHistoryDto(AccountHistoryDto accountHistoryDto, AccountHistory accountHistory) {
        if ( accountHistoryDto == null ) {
            return null;
        }

        if ( accountHistoryDto.getAccountHistoryKey() != null ) {
            if ( accountHistory.getAccountHistoryKey() == null ) {
                accountHistory.setAccountHistoryKey( new AccountHistoryKey() );
            }
            accountHistoryKeyDtoToAccountHistoryKey( accountHistoryDto.getAccountHistoryKey(), accountHistory.getAccountHistoryKey() );
        }
        if ( accountHistoryDto.getAccount() != null ) {
            if ( accountHistory.getAccount() == null ) {
                accountHistory.setAccount( new Account() );
            }
            accountDtoToAccount( accountHistoryDto.getAccount(), accountHistory.getAccount() );
        }
        if ( accountHistoryDto.getMovie() != null ) {
            if ( accountHistory.getMovie() == null ) {
                accountHistory.setMovie( new Movie() );
            }
            movieDtoToMovie( accountHistoryDto.getMovie(), accountHistory.getMovie() );
        }
        if ( accountHistoryDto.getDate() != null ) {
            accountHistory.setDate( accountHistoryDto.getDate() );
        }
        if ( accountHistoryDto.getTime_watched() != null ) {
            accountHistory.setTime_watched( accountHistoryDto.getTime_watched() );
        }

        return accountHistory;
    }

    protected MovieEvaluateKey movieEvaluateKeyDtoToMovieEvaluateKey(MovieEvaluateKeyDto movieEvaluateKeyDto) {
        if ( movieEvaluateKeyDto == null ) {
            return null;
        }

        MovieEvaluateKey movieEvaluateKey = new MovieEvaluateKey();

        movieEvaluateKey.setUserId( movieEvaluateKeyDto.getUserId() );
        movieEvaluateKey.setMovieId( movieEvaluateKeyDto.getMovieId() );

        return movieEvaluateKey;
    }

    protected MovieEvaluateKeyDto movieEvaluateKeyToMovieEvaluateKeyDto(MovieEvaluateKey movieEvaluateKey) {
        if ( movieEvaluateKey == null ) {
            return null;
        }

        MovieEvaluateKeyDto movieEvaluateKeyDto = new MovieEvaluateKeyDto();

        movieEvaluateKeyDto.setUserId( movieEvaluateKey.getUserId() );
        movieEvaluateKeyDto.setMovieId( movieEvaluateKey.getMovieId() );

        return movieEvaluateKeyDto;
    }

    protected void movieEvaluateKeyDtoToMovieEvaluateKey1(MovieEvaluateKeyDto movieEvaluateKeyDto, MovieEvaluateKey mappingTarget) {
        if ( movieEvaluateKeyDto == null ) {
            return;
        }

        if ( movieEvaluateKeyDto.getUserId() != null ) {
            mappingTarget.setUserId( movieEvaluateKeyDto.getUserId() );
        }
        if ( movieEvaluateKeyDto.getMovieId() != null ) {
            mappingTarget.setMovieId( movieEvaluateKeyDto.getMovieId() );
        }
    }

    protected void accountHistoryKeyDtoToAccountHistoryKey(AccountHistoryKeyDto accountHistoryKeyDto, AccountHistoryKey mappingTarget) {
        if ( accountHistoryKeyDto == null ) {
            return;
        }

        if ( accountHistoryKeyDto.getAccountId() != null ) {
            mappingTarget.setAccountId( accountHistoryKeyDto.getAccountId() );
        }
        if ( accountHistoryKeyDto.getMovieId() != null ) {
            mappingTarget.setMovieId( accountHistoryKeyDto.getMovieId() );
        }
    }

    protected void accountDtoToAccount(AccountDto accountDto, Account mappingTarget) {
        if ( accountDto == null ) {
            return;
        }

        if ( accountDto.getId() != null ) {
            mappingTarget.setId( accountDto.getId() );
        }
        if ( accountDto.getUsername() != null ) {
            mappingTarget.setUsername( accountDto.getUsername() );
        }
        if ( accountDto.getPassword() != null ) {
            mappingTarget.setPassword( accountDto.getPassword() );
        }
        if ( accountDto.getEnable() != null ) {
            mappingTarget.setEnable( accountDto.getEnable() );
        }
        if ( accountDto.getEmail() != null ) {
            mappingTarget.setEmail( accountDto.getEmail() );
        }
        if ( accountDto.getAvatar() != null ) {
            mappingTarget.setAvatar( accountDto.getAvatar() );
        }
        if ( accountDto.getLastname() != null ) {
            mappingTarget.setLastname( accountDto.getLastname() );
        }
        if ( accountDto.getFirstname() != null ) {
            mappingTarget.setFirstname( accountDto.getFirstname() );
        }
        if ( accountDto.getBirthday() != null ) {
            mappingTarget.setBirthday( accountDto.getBirthday() );
        }
        if ( accountDto.getWardId() != null ) {
            mappingTarget.setWardId( accountDto.getWardId() );
        }
        if ( accountDto.getAddressDetails() != null ) {
            mappingTarget.setAddressDetails( accountDto.getAddressDetails() );
        }
        if ( accountDto.getPhoneNumber() != null ) {
            mappingTarget.setPhoneNumber( accountDto.getPhoneNumber() );
        }
        if ( accountDto.getGender() != null ) {
            mappingTarget.setGender( accountDto.getGender() );
        }
    }

    protected void movieDtoToMovie(MovieDto movieDto, Movie mappingTarget) {
        if ( movieDto == null ) {
            return;
        }

        if ( movieDto.getId() != null ) {
            mappingTarget.setId( movieDto.getId() );
        }
        if ( movieDto.getName() != null ) {
            mappingTarget.setName( movieDto.getName() );
        }
        if ( movieDto.getPoster() != null ) {
            mappingTarget.setPoster( movieDto.getPoster() );
        }
        if ( movieDto.getSlug() != null ) {
            mappingTarget.setSlug( movieDto.getSlug() );
        }
        if ( movieDto.getImage_showing() != null ) {
            mappingTarget.setImage_showing( movieDto.getImage_showing() );
        }
        if ( movieDto.getDescription() != null ) {
            mappingTarget.setDescription( movieDto.getDescription() );
        }
        if ( movieDto.getQuality() != null ) {
            mappingTarget.setQuality( movieDto.getQuality() );
        }
        if ( movieDto.getMovieStatus() != null ) {
            mappingTarget.setMovieStatus( movieDto.getMovieStatus() );
        }
        if ( movieDto.getLinkTrailer() != null ) {
            mappingTarget.setLinkTrailer( movieDto.getLinkTrailer() );
        }
        if ( movieDto.getLinkMovie() != null ) {
            mappingTarget.setLinkMovie( movieDto.getLinkMovie() );
        }
        if ( movieDto.getReleaseDate() != null ) {
            mappingTarget.setReleaseDate( movieDto.getReleaseDate() );
        }
        if ( movieDto.getMovieDuration() != null ) {
            mappingTarget.setMovieDuration( movieDto.getMovieDuration() );
        }
        if ( movieDto.getViewNumber() != null ) {
            mappingTarget.setViewNumber( movieDto.getViewNumber() );
        }
        if ( movieDto.getTranslationStatus() != null ) {
            mappingTarget.setTranslationStatus( movieDto.getTranslationStatus() );
        }
        if ( movieDto.getCountryCode() != null ) {
            mappingTarget.setCountryCode( movieDto.getCountryCode() );
        }
        if ( movieDto.getMoviePrice() != null ) {
            mappingTarget.setMoviePrice( movieDto.getMoviePrice() );
        }
    }
}
