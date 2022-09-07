package com.example.demo.service;

import com.example.demo.dto.PromotionDto;

import java.util.List;

public interface PromotionService {
    List<PromotionDto> getAllPromotion();

    Boolean addPromotion(PromotionDto promotionDto);

    Boolean editPromotion(PromotionDto promotionDto);

    Boolean removePromotion(Integer id);
}
