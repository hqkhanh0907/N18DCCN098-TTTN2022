package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto implements Serializable {
    private Integer id;
    private String code_name;
    private String description;
    private Date start_date;
    private Date end_date;
    private Float percent_discount;
}
