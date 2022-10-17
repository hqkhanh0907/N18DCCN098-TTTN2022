package com.example.demo.dto.map;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.FavoriteMovieDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.dto.key.FavoriteMovieKeyDto;
import com.example.demo.model.Account;
import com.example.demo.model.FavoriteMovie;
import com.example.demo.model.Key.FavoriteMovieKey;
import com.example.demo.model.Movie;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class FavoriteMovieMapperImpl implements FavoriteMovieMapper {

    @Override
    public FavoriteMovie favoriteMovieDtoToFavoriteMovie(FavoriteMovieDto favoriteMovieDto) {
        if ( favoriteMovieDto == null ) {
            return null;
        }

        FavoriteMovie favoriteMovie = new FavoriteMovie();

        favoriteMovie.setId( favoriteMovieKeyDtoToFavoriteMovieKey( favoriteMovieDto.getId() ) );
        favoriteMovie.setAccount( accountDtoToAccount( favoriteMovieDto.getAccount() ) );
        favoriteMovie.setMovie( movieDtoToMovie( favoriteMovieDto.getMovie() ) );
        favoriteMovie.setDate( favoriteMovieDto.getDate() );

        return favoriteMovie;
    }

    @Override
    public FavoriteMovieDto favoriteMovieToFavoriteMovieDto(FavoriteMovie favoriteMovie) {
        if ( favoriteMovie == null ) {
            return null;
        }

        FavoriteMovieDto favoriteMovieDto = new FavoriteMovieDto();

        favoriteMovieDto.setId( favoriteMovieKeyToFavoriteMovieKeyDto( favoriteMovie.getId() ) );
        favoriteMovieDto.setAccount( accountToAccountDto( favoriteMovie.getAccount() ) );
        favoriteMovieDto.setMovie( movieToMovieDto( favoriteMovie.getMovie() ) );
        favoriteMovieDto.setDate( favoriteMovie.getDate() );

        return favoriteMovieDto;
    }

    @Override
    public FavoriteMovie updateFavoriteMovieFromFavoriteMovieDto(FavoriteMovieDto favoriteMovieDto, FavoriteMovie favoriteMovie) {
        if ( favoriteMovieDto == null ) {
            return null;
        }

        if ( favoriteMovieDto.getId() != null ) {
            if ( favoriteMovie.getId() == null ) {
                favoriteMovie.setId( new FavoriteMovieKey() );
            }
            favoriteMovieKeyDtoToFavoriteMovieKey1( favoriteMovieDto.getId(), favoriteMovie.getId() );
        }
        if ( favoriteMovieDto.getAccount() != null ) {
            if ( favoriteMovie.getAccount() == null ) {
                favoriteMovie.setAccount( new Account() );
            }
            accountDtoToAccount1( favoriteMovieDto.getAccount(), favoriteMovie.getAccount() );
        }
        if ( favoriteMovieDto.getMovie() != null ) {
            if ( favoriteMovie.getMovie() == null ) {
                favoriteMovie.setMovie( new Movie() );
            }
            movieDtoToMovie1( favoriteMovieDto.getMovie(), favoriteMovie.getMovie() );
        }
        if ( favoriteMovieDto.getDate() != null ) {
            favoriteMovie.setDate( favoriteMovieDto.getDate() );
        }

        return favoriteMovie;
    }

    protected FavoriteMovieKey favoriteMovieKeyDtoToFavoriteMovieKey(FavoriteMovieKeyDto favoriteMovieKeyDto) {
        if ( favoriteMovieKeyDto == null ) {
            return null;
        }

        FavoriteMovieKey favoriteMovieKey = new FavoriteMovieKey();

        favoriteMovieKey.setAccountId( favoriteMovieKeyDto.getAccountId() );
        favoriteMovieKey.setMovieId( favoriteMovieKeyDto.getMovieId() );

        return favoriteMovieKey;
    }

    protected Account accountDtoToAccount(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( accountDto.getId() );
        account.setUsername( accountDto.getUsername() );
        account.setPassword( accountDto.getPassword() );
        account.setEnable( accountDto.getEnable() );
        account.setEmail( accountDto.getEmail() );
        account.setAvatar( accountDto.getAvatar() );
        account.setLastname( accountDto.getLastname() );
        account.setFirstname( accountDto.getFirstname() );
        account.setBirthday( accountDto.getBirthday() );
        account.setWardId( accountDto.getWardId() );
        account.setAddressDetails( accountDto.getAddressDetails() );
        account.setPhoneNumber( accountDto.getPhoneNumber() );
        account.setGender( accountDto.getGender() );

        return account;
    }

    protected Movie movieDtoToMovie(MovieDto movieDto) {
        if ( movieDto == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setId( movieDto.getId() );
        movie.setName( movieDto.getName() );
        movie.setPoster( movieDto.getPoster() );
        movie.setSlug( movieDto.getSlug() );
        movie.setImage_showing( movieDto.getImage_showing() );
        movie.setDescription( movieDto.getDescription() );
        movie.setQuality( movieDto.getQuality() );
        movie.setMovieStatus( movieDto.getMovieStatus() );
        movie.setLinkTrailer( movieDto.getLinkTrailer() );
        movie.setLinkMovie( movieDto.getLinkMovie() );
        movie.setReleaseDate( movieDto.getReleaseDate() );
        movie.setMovieDuration( movieDto.getMovieDuration() );
        movie.setViewNumber( movieDto.getViewNumber() );
        movie.setTranslationStatus( movieDto.getTranslationStatus() );
        movie.setCountryCode( movieDto.getCountryCode() );
        movie.setMoviePrice( movieDto.getMoviePrice() );

        return movie;
    }

    protected FavoriteMovieKeyDto favoriteMovieKeyToFavoriteMovieKeyDto(FavoriteMovieKey favoriteMovieKey) {
        if ( favoriteMovieKey == null ) {
            return null;
        }

        FavoriteMovieKeyDto favoriteMovieKeyDto = new FavoriteMovieKeyDto();

        favoriteMovieKeyDto.setAccountId( favoriteMovieKey.getAccountId() );
        favoriteMovieKeyDto.setMovieId( favoriteMovieKey.getMovieId() );

        return favoriteMovieKeyDto;
    }

    protected AccountDto accountToAccountDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setId( account.getId() );
        accountDto.setUsername( account.getUsername() );
        accountDto.setPassword( account.getPassword() );
        accountDto.setEnable( account.getEnable() );
        accountDto.setEmail( account.getEmail() );
        accountDto.setAvatar( account.getAvatar() );
        accountDto.setLastname( account.getLastname() );
        accountDto.setFirstname( account.getFirstname() );
        accountDto.setBirthday( account.getBirthday() );
        accountDto.setWardId( account.getWardId() );
        accountDto.setAddressDetails( account.getAddressDetails() );
        accountDto.setPhoneNumber( account.getPhoneNumber() );
        accountDto.setGender( account.getGender() );

        return accountDto;
    }

    protected MovieDto movieToMovieDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        movieDto.setId( movie.getId() );
        movieDto.setName( movie.getName() );
        movieDto.setImage_showing( movie.getImage_showing() );
        movieDto.setPoster( movie.getPoster() );
        movieDto.setSlug( movie.getSlug() );
        movieDto.setDescription( movie.getDescription() );
        movieDto.setQuality( movie.getQuality() );
        movieDto.setMovieStatus( movie.getMovieStatus() );
        movieDto.setLinkTrailer( movie.getLinkTrailer() );
        movieDto.setLinkMovie( movie.getLinkMovie() );
        movieDto.setReleaseDate( movie.getReleaseDate() );
        movieDto.setMovieDuration( movie.getMovieDuration() );
        movieDto.setViewNumber( movie.getViewNumber() );
        movieDto.setTranslationStatus( movie.getTranslationStatus() );
        movieDto.setCountryCode( movie.getCountryCode() );
        movieDto.setMoviePrice( movie.getMoviePrice() );

        return movieDto;
    }

    protected void favoriteMovieKeyDtoToFavoriteMovieKey1(FavoriteMovieKeyDto favoriteMovieKeyDto, FavoriteMovieKey mappingTarget) {
        if ( favoriteMovieKeyDto == null ) {
            return;
        }

        if ( favoriteMovieKeyDto.getAccountId() != null ) {
            mappingTarget.setAccountId( favoriteMovieKeyDto.getAccountId() );
        }
        if ( favoriteMovieKeyDto.getMovieId() != null ) {
            mappingTarget.setMovieId( favoriteMovieKeyDto.getMovieId() );
        }
    }

    protected void accountDtoToAccount1(AccountDto accountDto, Account mappingTarget) {
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

    protected void movieDtoToMovie1(MovieDto movieDto, Movie mappingTarget) {
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
