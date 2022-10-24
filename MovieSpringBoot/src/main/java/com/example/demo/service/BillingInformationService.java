package com.example.demo.service;

import com.example.demo.dto.BillingInformationDto;
import com.example.demo.model.Key.BillingInformationKey;

import java.util.List;
import java.util.Map;

public interface BillingInformationService {
    Boolean updateBillInformation(BillingInformationDto billingInformationDto);

    Boolean checkPay(Integer accountId, Integer movieId);

    void deleteBillByAccount(Integer id);

    Boolean checkPromoExitBill(Integer promoId);

    BillingInformationDto getBill(BillingInformationKey billingInformationKey);

    Boolean checkExitPay(Integer accountId, Integer movieId);

    List<BillingInformationDto> getAll();

    Boolean addInfoBill(BillingInformationDto billingInformation);

    Map<String, Object> getChartByYear();

    Map<String, Object> getChartByMonth(String year);

    List<?> getListYear();
}
