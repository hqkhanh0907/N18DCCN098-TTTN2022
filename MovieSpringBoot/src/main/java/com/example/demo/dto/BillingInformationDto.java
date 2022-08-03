package com.example.demo.dto;

import com.example.demo.dto.key.BillingInformationKeyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingInformationDto implements Serializable {
    private BillingInformationKeyDto billingInformationKey;
    private MovieDto movie;
    private AccountDto account;
    private PromotionDto promotion;
    private Integer status;
}
