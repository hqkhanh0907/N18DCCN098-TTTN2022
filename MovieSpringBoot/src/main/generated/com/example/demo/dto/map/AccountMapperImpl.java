package com.example.demo.dto.map;

import com.example.demo.dto.AccountDto;
import com.example.demo.model.Account;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account accountDtoToAccount(AccountDto accountDto) {
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

    @Override
    public AccountDto accountToAccountDto(Account account) {
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

    @Override
    public Account updateAccountFromAccountDto(AccountDto accountDto, Account account) {
        if ( accountDto == null ) {
            return null;
        }

        if ( accountDto.getId() != null ) {
            account.setId( accountDto.getId() );
        }
        if ( accountDto.getUsername() != null ) {
            account.setUsername( accountDto.getUsername() );
        }
        if ( accountDto.getPassword() != null ) {
            account.setPassword( accountDto.getPassword() );
        }
        if ( accountDto.getEnable() != null ) {
            account.setEnable( accountDto.getEnable() );
        }
        if ( accountDto.getEmail() != null ) {
            account.setEmail( accountDto.getEmail() );
        }
        if ( accountDto.getAvatar() != null ) {
            account.setAvatar( accountDto.getAvatar() );
        }
        if ( accountDto.getLastname() != null ) {
            account.setLastname( accountDto.getLastname() );
        }
        if ( accountDto.getFirstname() != null ) {
            account.setFirstname( accountDto.getFirstname() );
        }
        if ( accountDto.getBirthday() != null ) {
            account.setBirthday( accountDto.getBirthday() );
        }
        if ( accountDto.getWardId() != null ) {
            account.setWardId( accountDto.getWardId() );
        }
        if ( accountDto.getAddressDetails() != null ) {
            account.setAddressDetails( accountDto.getAddressDetails() );
        }
        if ( accountDto.getPhoneNumber() != null ) {
            account.setPhoneNumber( accountDto.getPhoneNumber() );
        }
        if ( accountDto.getGender() != null ) {
            account.setGender( accountDto.getGender() );
        }

        return account;
    }
}
