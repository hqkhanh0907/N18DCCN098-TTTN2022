package com.example.demo.service.implement;

import com.example.demo.dto.PromotionDto;
import com.example.demo.dto.map.PromotionMapper;
import com.example.demo.model.Promotion;
import com.example.demo.repository.PromotionRepository;
import com.example.demo.service.BillingInformationService;
import com.example.demo.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;
    private final BillingInformationService billingInformationService;

    @Override
    public List<PromotionDto> getAllPromotion() {
        return promotionRepository.findAll().stream().map(promotion -> {
            return promotionMapper.promotionToPromotionDto(promotion);
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean addPromotion(PromotionDto promotionDto) {
        if (checkCodeExit(promotionDto.getCode_name()) == false) {
            Promotion promotion = promotionMapper.promotionDtoToPromotion(promotionDto);
            try {
                promotionRepository.save(promotion);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public Boolean editPromotion(PromotionDto promotionDto) {
        Promotion promotion = getById(promotionDto.getId());
        if (promotion.getCode_name().equals(promotionDto.getCode_name())) {
            promotion = promotionMapper.promotionDtoToPromotion(promotionDto);
            promotionRepository.save(promotion);
            return true;
        } else {
            if (checkCodeExit(promotionDto.getCode_name()) == false) {
                promotion = promotionMapper.promotionDtoToPromotion(promotionDto);
                promotionRepository.save(promotion);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean removePromotion(Integer id) {
        Promotion promotion = getById(id);
        if (checkPromoInBill(id) == false) {
            promotionRepository.deletePromoId(promotion.getId());
            return true;
        }
        return false;
    }

    private Boolean checkPromoInBill(Integer promoId) {
        if (billingInformationService.checkPromoExitBill(promoId)) {
            throw new RuntimeException("Code already in use, cannot be deleted");
        } else {
            return false;
        }
    }

    public Boolean checkTime(Promotion promotion) {
        Date today = new Date();
        if (today.before(promotion.getEnd_date()) && today.after(promotion.getStart_date())) {
            return true;
        }
        return false;
    }

    private Promotion getById(Integer id) {
        Promotion promotion = promotionRepository.findById(id).orElse(null);
        if (Objects.isNull(promotion)) {
            throw new RuntimeException("Promotion not found!");
        } else {
            return promotion;
        }
    }

    private Boolean checkCodeExit(String codename) {
        List<Promotion> promotions = promotionRepository.findAll();
        for (Promotion promotion : promotions) {
            if (promotion.getCode_name().equals(codename)) {
                throw new RuntimeException("Promotion code name exit with " + codename);
            }
        }
        return false;
    }
}
