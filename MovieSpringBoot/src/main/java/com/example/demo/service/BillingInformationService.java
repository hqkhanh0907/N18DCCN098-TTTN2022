package com.example.demo.service;

import com.example.demo.dto.BillingInformationDto;

public interface BillingInformationService {
    Boolean updateBillInformation(BillingInformationDto billingInformationDto);

    Boolean checkPay(Integer accountId, Integer movieId);
}
