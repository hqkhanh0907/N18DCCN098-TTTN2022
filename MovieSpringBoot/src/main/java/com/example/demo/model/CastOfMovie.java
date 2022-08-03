package com.example.demo.model;

import com.example.demo.model.Key.CastOfMovieKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("castId")
    @JoinColumn(name = "cast_id")
    private Cast cast;
}
