package com.example.demo.dto.map;

import com.example.demo.dto.AccountHistoryDto;
import com.example.demo.model.AccountHistory;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AccountHistoryMapper {
    AccountHistory accountHistoryDtoToAccountHistory(AccountHistoryDto accountHistoryDto);

    AccountHistoryDto accountHistoryToAccountHistoryDto(AccountHistory accountHistory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountHistory updateAccountHistoryFromAccountHistoryDto(AccountHistoryDto accountHistoryDto, @MappingTarget AccountHistory accountHistory);
}
