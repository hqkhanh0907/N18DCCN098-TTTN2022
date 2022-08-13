package com.example.demo.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "enable")
    private Boolean enable;

    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "ward_id")
    private Integer wardId;

    @Column(name = "details_address")
    private String addressDetails;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "gender")
    private Boolean gender;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    private List<GroupOfRoles> groupOfRoleses = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    private List<MovieEvaluate> movieEvaluates = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    private List<FavoriteMovie> favoriteMovies = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    private List<AccountHistory> accountHistories = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    private List<BillingInformation> billingInformations = new ArrayList<>();

}
