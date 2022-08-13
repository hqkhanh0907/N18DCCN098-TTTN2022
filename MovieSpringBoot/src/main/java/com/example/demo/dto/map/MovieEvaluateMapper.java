package com.example.demo.dto.map;

import com.example.demo.dto.AccountHistoryDto;
import com.example.demo.dto.MovieEvaluateDto;
import com.example.demo.model.AccountHistory;
import com.example.demo.model.MovieEvaluate;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MovieEvaluateMapper {
    MovieEvaluate movieEvaluateDtoToMovieEvaluate(MovieEvaluateDto movieEvaluateDto);

    MovieEvaluateDto movieEvaluateToMovieEvaluateDto(MovieEvaluate movieEvaluate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MovieEvaluate updateMovieEvaluateFromMovieEvaluateDto(MovieEvaluateDto movieEvaluateDto, @MappingTarget MovieEvaluate movieEvaluate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountHistory updateAccountHistoryFromAccountHistoryDto(AccountHistoryDto accountHistoryDto, @MappingTarget AccountHistory accountHistory);
}
