package com.example.demo.model;

import com.example.demo.model.Key.BillingInformationKey;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "billing_information")
public class BillingInformation {
    @EmbeddedId
    private BillingInformationKey billingInformationKey = new BillingInformationKey();


    @ManyToOne(cascade = {CascadeType.ALL})
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    @EqualsAndHashCode.Exclude
    private Movie movie;

    @ManyToOne(cascade = {CascadeType.ALL})
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    @EqualsAndHashCode.Exclude
    private Account account;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "promotion_id")
    @EqualsAndHashCode.Exclude
    private Promotion promotion;

    @Column(name = "status")
    private Integer status;

}
