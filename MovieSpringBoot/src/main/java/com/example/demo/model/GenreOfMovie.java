package com.example.demo.model;

import com.example.demo.model.Key.GenreOfMovieKey;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy = false)
@Table(name = "genre_of_movie")
public class GenreOfMovie {
    @EmbeddedId
    private GenreOfMovieKey id = new GenreOfMovieKey();


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    @EqualsAndHashCode.Exclude
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @MapsId("genreId")
    @JoinColumn(name = "genre_id")
    @EqualsAndHashCode.Exclude
    private Genre genre;
}
