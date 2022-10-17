package com.example.demo.dto.map;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.BillingInformationDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.dto.PromotionDto;
import com.example.demo.dto.key.BillingInformationKeyDto;
import com.example.demo.model.Account;
import com.example.demo.model.BillingInformation;
import com.example.demo.model.Key.BillingInformationKey;
import com.example.demo.model.Movie;
import com.example.demo.model.Promotion;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class BillingInformationMapperImpl implements BillingInformationMapper {

    @Override
    public BillingInformation billingInformationDtoToBillingInformation(BillingInformationDto billingInformationDto) {
        if ( billingInformationDto == null ) {
            return null;
        }

        BillingInformation billingInformation = new BillingInformation();

        billingInformation.setBillingInformationKey( billingInformationKeyDtoToBillingInformationKey( billingInformationDto.getBillingInformationKey() ) );
        billingInformation.setMovie( movieDtoToMovie( billingInformationDto.getMovie() ) );
        billingInformation.setAccount( accountDtoToAccount( billingInformationDto.getAccount() ) );
        billingInformation.setPromotion( promotionDtoToPromotion( billingInformationDto.getPromotion() ) );
        billingInformation.setStatus( billingInformationDto.getStatus() );
        billingInformation.setPrice( billingInformationDto.getPrice() );
        billingInformation.setDate( billingInformationDto.getDate() );

        return billingInformation;
    }

    @Override
    public BillingInformationDto billingInformationToBillingInformationDto(BillingInformation billingInformation) {
        if ( billingInformation == null ) {
            return null;
        }

        BillingInformationDto billingInformationDto = new BillingInformationDto();

        billingInformationDto.setBillingInformationKey( billingInformationKeyToBillingInformationKeyDto( billingInformation.getBillingInformationKey() ) );
        billingInformationDto.setMovie( movieToMovieDto( billingInformation.getMovie() ) );
        billingInformationDto.setAccount( accountToAccountDto( billingInformation.getAccount() ) );
        billingInformationDto.setPromotion( promotionToPromotionDto( billingInformation.getPromotion() ) );
        billingInformationDto.setStatus( billingInformation.getStatus() );
        billingInformationDto.setPrice( billingInformation.getPrice() );
        billingInformationDto.setDate( billingInformation.getDate() );

        return billingInformationDto;
    }

    @Override
    public BillingInformation updateBillingInformationFromBillingInformationDto(BillingInformationDto billingInformationDto, BillingInformation billingInformation) {
        if ( billingInformationDto == null ) {
            return null;
        }

        if ( billingInformationDto.getBillingInformationKey() != null ) {
            if ( billingInformation.getBillingInformationKey() == null ) {
                billingInformation.setBillingInformationKey( new BillingInformationKey() );
            }
            billingInformationKeyDtoToBillingInformationKey1( billingInformationDto.getBillingInformationKey(), billingInformation.getBillingInformationKey() );
        }
        if ( billingInformationDto.getMovie() != null ) {
            if ( billingInformation.getMovie() == null ) {
                billingInformation.setMovie( new Movie() );
            }
            movieDtoToMovie1( billingInformationDto.getMovie(), billingInformation.getMovie() );
        }
        if ( billingInformationDto.getAccount() != null ) {
            if ( billingInformation.getAccount() == null ) {
                billingInformation.setAccount( new Account() );
            }
            accountDtoToAccount1( billingInformationDto.getAccount(), billingInformation.getAccount() );
        }
        if ( billingInformationDto.getPromotion() != null ) {
            if ( billingInformation.getPromotion() == null ) {
                billingInformation.setPromotion( new Promotion() );
            }
            promotionDtoToPromotion1( billingInformationDto.getPromotion(), billingInformation.getPromotion() );
        }
        if ( billingInformationDto.getStatus() != null ) {
            billingInformation.setStatus( billingInformationDto.getStatus() );
        }
        if ( billingInformationDto.getPrice() != null ) {
            billingInformation.setPrice( billingInformationDto.getPrice() );
        }
        if ( billingInformationDto.getDate() != null ) {
            billingInformation.setDate( billingInformationDto.getDate() );
        }

        return billingInformation;
    }

    protected BillingInformationKey billingInformationKeyDtoToBillingInformationKey(BillingInformationKeyDto billingInformationKeyDto) {
        if ( billingInformationKeyDto == null ) {
            return null;
        }

        BillingInformationKey billingInformationKey = new BillingInformationKey();

        billingInformationKey.setAccountId( billingInformationKeyDto.getAccountId() );
        billingInformationKey.setMovieId( billingInformationKeyDto.getMovieId() );

        return billingInformationKey;
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

    protected Promotion promotionDtoToPromotion(PromotionDto promotionDto) {
        if ( promotionDto == null ) {
            return null;
        }

        Promotion promotion = new Promotion();

        promotion.setId( promotionDto.getId() );
        promotion.setCode_name( promotionDto.getCode_name() );
        promotion.setDescription( promotionDto.getDescription() );
        promotion.setStart_date( promotionDto.getStart_date() );
        promotion.setEnd_date( promotionDto.getEnd_date() );
        promotion.setPercent_discount( promotionDto.getPercent_discount() );

        return promotion;
    }

    protected BillingInformationKeyDto billingInformationKeyToBillingInformationKeyDto(BillingInformationKey billingInformationKey) {
        if ( billingInformationKey == null ) {
            return null;
        }

        BillingInformationKeyDto billingInformationKeyDto = new BillingInformationKeyDto();

        billingInformationKeyDto.setAccountId( billingInformationKey.getAccountId() );
        billingInformationKeyDto.setMovieId( billingInformationKey.getMovieId() );

        return billingInformationKeyDto;
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

    protected PromotionDto promotionToPromotionDto(Promotion promotion) {
        if ( promotion == null ) {
            return null;
        }

        PromotionDto promotionDto = new PromotionDto();

        promotionDto.setId( promotion.getId() );
        promotionDto.setCode_name( promotion.getCode_name() );
        promotionDto.setDescription( promotion.getDescription() );
        promotionDto.setStart_date( promotion.getStart_date() );
        promotionDto.setEnd_date( promotion.getEnd_date() );
        promotionDto.setPercent_discount( promotion.getPercent_discount() );

        return promotionDto;
    }

    protected void billingInformationKeyDtoToBillingInformationKey1(BillingInformationKeyDto billingInformationKeyDto, BillingInformationKey mappingTarget) {
        if ( billingInformationKeyDto == null ) {
            return;
        }

        if ( billingInformationKeyDto.getAccountId() != null ) {
            mappingTarget.setAccountId( billingInformationKeyDto.getAccountId() );
        }
        if ( billingInformationKeyDto.getMovieId() != null ) {
            mappingTarget.setMovieId( billingInformationKeyDto.getMovieId() );
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

    protected void promotionDtoToPromotion1(PromotionDto promotionDto, Promotion mappingTarget) {
        if ( promotionDto == null ) {
            return;
        }

        if ( promotionDto.getId() != null ) {
            mappingTarget.setId( promotionDto.getId() );
        }
        if ( promotionDto.getCode_name() != null ) {
            mappingTarget.setCode_name( promotionDto.getCode_name() );
        }
        if ( promotionDto.getDescription() != null ) {
            mappingTarget.setDescription( promotionDto.getDescription() );
        }
        if ( promotionDto.getStart_date() != null ) {
            mappingTarget.setStart_date( promotionDto.getStart_date() );
        }
        if ( promotionDto.getEnd_date() != null ) {
            mappingTarget.setEnd_date( promotionDto.getEnd_date() );
        }
        if ( promotionDto.getPercent_discount() != null ) {
            mappingTarget.setPercent_discount( promotionDto.getPercent_discount() );
        }
    }
}
