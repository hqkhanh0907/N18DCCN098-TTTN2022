package com.example.demo.dto.map;

import com.example.demo.dto.AccountDto;
import com.example.demo.model.Account;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AccountMapper {
    Account accountDtoToAccount(AccountDto accountDto);

    AccountDto accountToAccountDto(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account updateAccountFromAccountDto(AccountDto accountDto, @MappingTarget Account account);
}
