package com.example.demo.dto.map;

import com.example.demo.dto.PromotionDto;
import com.example.demo.model.Promotion;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PromotionMapper {
    Promotion promotionDtoToPromotion(PromotionDto promotionDto);

    PromotionDto promotionToPromotionDto(Promotion promotion);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Promotion updatePromotionFromPromotionDto(PromotionDto promotionDto, @MappingTarget Promotion promotion);
}
