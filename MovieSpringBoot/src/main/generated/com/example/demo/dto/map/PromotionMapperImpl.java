package com.example.demo.dto.map;

import com.example.demo.dto.PromotionDto;
import com.example.demo.model.Promotion;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:16:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
@Component
public class PromotionMapperImpl implements PromotionMapper {

    @Override
    public Promotion promotionDtoToPromotion(PromotionDto promotionDto) {
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

    @Override
    public PromotionDto promotionToPromotionDto(Promotion promotion) {
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

    @Override
    public Promotion updatePromotionFromPromotionDto(PromotionDto promotionDto, Promotion promotion) {
        if ( promotionDto == null ) {
            return null;
        }

        if ( promotionDto.getId() != null ) {
            promotion.setId( promotionDto.getId() );
        }
        if ( promotionDto.getCode_name() != null ) {
            promotion.setCode_name( promotionDto.getCode_name() );
        }
        if ( promotionDto.getDescription() != null ) {
            promotion.setDescription( promotionDto.getDescription() );
        }
        if ( promotionDto.getStart_date() != null ) {
            promotion.setStart_date( promotionDto.getStart_date() );
        }
        if ( promotionDto.getEnd_date() != null ) {
            promotion.setEnd_date( promotionDto.getEnd_date() );
        }
        if ( promotionDto.getPercent_discount() != null ) {
            promotion.setPercent_discount( promotionDto.getPercent_discount() );
        }

        return promotion;
    }
}
