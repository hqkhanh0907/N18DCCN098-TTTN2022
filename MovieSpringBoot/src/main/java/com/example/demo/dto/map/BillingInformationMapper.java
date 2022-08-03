package com.example.demo.dto.map;

import com.example.demo.dto.BillingInformationDto;
import com.example.demo.model.BillingInformation;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BillingInformationMapper {
    BillingInformation billingInformationDtoToBillingInformation(BillingInformationDto billingInformationDto);

    BillingInformationDto billingInformationToBillingInformationDto(BillingInformation billingInformation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BillingInformation updateBillingInformationFromBillingInformationDto(BillingInformationDto billingInformationDto, @MappingTarget BillingInformation billingInformation);
}
