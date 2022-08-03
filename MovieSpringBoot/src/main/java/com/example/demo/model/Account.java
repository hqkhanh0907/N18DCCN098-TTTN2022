package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@ToString
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

    @NotNull
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "ward_id")
    private String wardId;

    @Column(name = "address_details")
    private String addressDetails;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "gender")
    private Boolean gender;

    @OneToMany(mappedBy = "account", cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<GroupOfRoles> groupOfRoleses = new ArrayList<>();


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieEvaluate> movieEvaluates = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteMovie> favoriteMovies = new ArrayList<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    private List<Promotion> promotions = new ArrayList<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    private List<AccountHistory> accountHistories = new ArrayList<>();

}
