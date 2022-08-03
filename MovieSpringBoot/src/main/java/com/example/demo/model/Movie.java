package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "Movie's name cannot be empty")
    private String name;

    @Column(name = "poster")
    @NotNull(message = "Movie's poster cannot be empty")
    private String poster;

    @NotNull
    @Column(name = "slug")
    private String slug;

    @NotNull
    @Column(name = "image_showing")
    private String image_showing;

    @Column(name = "description")
    @NotBlank(message = "Movie's description cannot be empty")
    private String description;

    @NotNull
    @Column(name = "quality")
    private String quality;


    @Column(name = "movie_status")
    @NotNull(message = "Movie's status cannot be empty")
    private Integer movieStatus;

    @Column(name = "link_trailer")
    private String linkTrailer;

    @Column(name = "link_movie")
    @NotNull(message = "Movie's link cannot be empty")
    private String linkMovie;

    @Column(name = "release_date")
    @NotNull(message = "Movie' release date cannot be empty")
    @PastOrPresent(message = "Release time is not greater than present")
    private Date releaseDate;

    @Column(name = "movie_duration")
    private Float movieDuration;

    @Column(name = "view_num")
    private Integer viewNumber;

    @NotNull
    @Column(name = "translation_status")
    private Integer translationStatus;

    @NotNull
    @Column(name = "country_code")
    private Integer countryCode;

    @Column(name = "movie_price")
    private Float moviePrice;

    @ToString.Exclude
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DirectorOfMovie> directorOfMovies = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteMovie> favoriteMovies = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenreOfMovie> genreOfMovies = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountHistory> accountHistories = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieEvaluate> movieEvaluates = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CastOfMovie> castOfMovies = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillingInformation> billingInformations = new ArrayList<>();

}
