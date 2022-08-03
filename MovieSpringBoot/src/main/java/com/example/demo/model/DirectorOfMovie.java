package com.example.demo.model;

import com.example.demo.model.Key.DirectorOfMovieKey;
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
@Table(name = "director_of_movie")
public class DirectorOfMovie {
    @EmbeddedId
    private DirectorOfMovieKey id = new DirectorOfMovieKey();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("directorId")
    @JoinColumn(name = "dricetor_id")
    private Director director;
}
