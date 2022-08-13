package com.example.demo.service.implement;

import com.example.demo.dto.BillingInformationDto;
import com.example.demo.model.BillingInformation;
import com.example.demo.model.Key.BillingInformationKey;
import com.example.demo.repository.BillingInformationRepository;
import com.example.demo.service.BillingInformationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class BillingInformationServiceImpl implements BillingInformationService {
    private final BillingInformationRepository billingInformationRepository;


    @Override
    public Boolean updateBillInformation(BillingInformationDto billingInformationDto) {
        BillingInformation billingInformation = billingInformationRepository.findById(
                        new BillingInformationKey(
                                billingInformationDto.getBillingInformationKey().getAccountId(),
                                billingInformationDto.getBillingInformationKey().getMovieId()))
                .orElse(null);
        billingInformation.setStatus(billingInformationDto.getStatus());
        try {
            billingInformationRepository.save(billingInformation);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean checkPay(Integer accountId, Integer movieId) {
        BillingInformationKey billingInformationKey = new BillingInformationKey(accountId, movieId);
        BillingInformation billingInformation = billingInformationRepository.findById(billingInformationKey).orElse(null);
        if (Objects.isNull(billingInformation)) {
            return false;
        } else {
            if (billingInformation.getStatus().equals(1)){
                return true;
            } else return false;
        }
    }
}
