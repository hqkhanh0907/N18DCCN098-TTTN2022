package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code_name")
    private String code_name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    @Column(name = "percent_discount")
    private Float percent_discount;


    @OneToMany(mappedBy = "promotion", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    private List<BillingInformation> billingInformations = new ArrayList<>();

}
