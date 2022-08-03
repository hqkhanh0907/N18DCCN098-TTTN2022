package com.example.demo.dto.map;

import com.example.demo.dto.AccountHistoryDto;
import com.example.demo.model.AccountHistory;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AccountHistoryMapper {
    AccountHistory accountHistoryDtoToAccountHistory(AccountHistoryDto accountHistoryDto);

    AccountHistoryDto accountHistoryToAccountHistoryDto(AccountHistory accountHistory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountHistory updateAccountHistoryFromAccountHistoryDto(AccountHistoryDto accountHistoryDto, @MappingTarget AccountHistory accountHistory);
}
