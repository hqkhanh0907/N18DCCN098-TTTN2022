package com.example.demo.dto.map;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.AccountHistoryDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.dto.key.AccountHistoryKeyDto;
import com.example.demo.model.Account;
import com.example.demo.model.AccountHistory;
import com.example.demo.model.Key.AccountHistoryKey;
import com.example.demo.model.Movie;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class AccountHistoryMapperImpl implements AccountHistoryMapper {

    @Override
    public AccountHistory accountHistoryDtoToAccountHistory(AccountHistoryDto accountHistoryDto) {
        if ( accountHistoryDto == null ) {
            return null;
        }

        AccountHistory accountHistory = new AccountHistory();

        accountHistory.setAccountHistoryKey( accountHistoryKeyDtoToAccountHistoryKey( accountHistoryDto.getAccountHistoryKey() ) );
        accountHistory.setAccount( accountDtoToAccount( accountHistoryDto.getAccount() ) );
        accountHistory.setMovie( movieDtoToMovie( accountHistoryDto.getMovie() ) );
        accountHistory.setDate( accountHistoryDto.getDate() );
        accountHistory.setTime_watched( accountHistoryDto.getTime_watched() );

        return accountHistory;
    }

    @Override
    public AccountHistoryDto accountHistoryToAccountHistoryDto(AccountHistory accountHistory) {
        if ( accountHistory == null ) {
            return null;
        }

        AccountHistoryDto accountHistoryDto = new AccountHistoryDto();

        accountHistoryDto.setAccountHistoryKey( accountHistoryKeyToAccountHistoryKeyDto( accountHistory.getAccountHistoryKey() ) );
        accountHistoryDto.setAccount( accountToAccountDto( accountHistory.getAccount() ) );
        accountHistoryDto.setMovie( movieToMovieDto( accountHistory.getMovie() ) );
        accountHistoryDto.setDate( accountHistory.getDate() );
        accountHistoryDto.setTime_watched( accountHistory.getTime_watched() );

        return accountHistoryDto;
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
            accountHistoryKeyDtoToAccountHistoryKey1( accountHistoryDto.getAccountHistoryKey(), accountHistory.getAccountHistoryKey() );
        }
        if ( accountHistoryDto.getAccount() != null ) {
            if ( accountHistory.getAccount() == null ) {
                accountHistory.setAccount( new Account() );
            }
            accountDtoToAccount1( accountHistoryDto.getAccount(), accountHistory.getAccount() );
        }
        if ( accountHistoryDto.getMovie() != null ) {
            if ( accountHistory.getMovie() == null ) {
                accountHistory.setMovie( new Movie() );
            }
            movieDtoToMovie1( accountHistoryDto.getMovie(), accountHistory.getMovie() );
        }
        if ( accountHistoryDto.getDate() != null ) {
            accountHistory.setDate( accountHistoryDto.getDate() );
        }
        if ( accountHistoryDto.getTime_watched() != null ) {
            accountHistory.setTime_watched( accountHistoryDto.getTime_watched() );
        }

        return accountHistory;
    }

    protected AccountHistoryKey accountHistoryKeyDtoToAccountHistoryKey(AccountHistoryKeyDto accountHistoryKeyDto) {
        if ( accountHistoryKeyDto == null ) {
            return null;
        }

        AccountHistoryKey accountHistoryKey = new AccountHistoryKey();

        accountHistoryKey.setAccountId( accountHistoryKeyDto.getAccountId() );
        accountHistoryKey.setMovieId( accountHistoryKeyDto.getMovieId() );

        return accountHistoryKey;
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

    protected AccountHistoryKeyDto accountHistoryKeyToAccountHistoryKeyDto(AccountHistoryKey accountHistoryKey) {
        if ( accountHistoryKey == null ) {
            return null;
        }

        AccountHistoryKeyDto accountHistoryKeyDto = new AccountHistoryKeyDto();

        accountHistoryKeyDto.setAccountId( accountHistoryKey.getAccountId() );
        accountHistoryKeyDto.setMovieId( accountHistoryKey.getMovieId() );

        return accountHistoryKeyDto;
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

    protected void accountHistoryKeyDtoToAccountHistoryKey1(AccountHistoryKeyDto accountHistoryKeyDto, AccountHistoryKey mappingTarget) {
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
