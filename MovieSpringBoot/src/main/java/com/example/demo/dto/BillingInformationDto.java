package com.example.demo.dto;

import com.example.demo.dto.key.BillingInformationKeyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.example.demo.model.BillingInformation} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingInformationDto implements Serializable {
    private BillingInformationKeyDto billingInformationKey = new BillingInformationKeyDto();
    private MovieDto movie;
    private AccountDto account;
    private PromotionDto promotion;
    private Integer status;
    private Double price;
    private Date date;
}