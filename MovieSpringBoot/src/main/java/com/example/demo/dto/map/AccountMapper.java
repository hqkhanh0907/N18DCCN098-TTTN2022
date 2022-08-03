package com.example.demo.dto.map;

import com.example.demo.dto.AccountDto;
import com.example.demo.model.Account;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AccountMapper {
    Account AccountDtoToAccount(AccountDto AccountDto);

    AccountDto accountToAccountDto(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account updateAccountFromAccountDto(AccountDto AccountDto, @MappingTarget Account account);
}
