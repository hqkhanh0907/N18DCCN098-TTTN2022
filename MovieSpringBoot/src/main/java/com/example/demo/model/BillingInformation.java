package com.example.demo.model;

import com.example.demo.model.Key.BillingInformationKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "billing_information")
public class BillingInformation {
    @EmbeddedId
    private BillingInformationKey billingInformationKey;

    @ManyToOne(cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Column(name = "status")
    private Integer status;



}
