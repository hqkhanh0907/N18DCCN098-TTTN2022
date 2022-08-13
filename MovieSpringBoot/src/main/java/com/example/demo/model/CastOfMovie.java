package com.example.demo.model;

import com.example.demo.model.Key.CastOfMovieKey;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cast_of_movie")
public class CastOfMovie {
    @EmbeddedId
    private CastOfMovieKey id = new CastOfMovieKey();

    @Column(name = "cast_character")
    private String castCharacter;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    @EqualsAndHashCode.Exclude
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @MapsId("castId")
    @JoinColumn(name = "cast_id")
    @EqualsAndHashCode.Exclude
    private Cast cast;
}
