package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto implements Serializable {
    private Integer id;
    @NotBlank(message = "Movie's name cannot be empty")
    private String name;
    @NotNull
    private String image_showing;
    @NotNull(message = "Movie's poster cannot be empty")
    private String poster;
    @NotNull
    private String slug;
    @NotBlank(message = "Movie's description cannot be empty")
    private String description;
    @NotNull
    private String quality;
    @NotNull(message = "Movie's status cannot be empty")
    private Integer movieStatus;
    private String linkTrailer;
    @NotNull(message = "Movie's link cannot be empty")
    private String linkMovie;
    @NotNull(message = "Movie' release date cannot be empty")
    @PastOrPresent(message = "Release time is not greater than present")
    private Date releaseDate;
    private Float movieDuration;
    private Integer viewNumber;
    @NotNull
    private Integer translationStatus;
    @NotNull
    private Integer countryCode;
    private Float moviePrice;
}
