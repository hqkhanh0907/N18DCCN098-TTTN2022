package com.example.demo.model;

import com.example.demo.model.Key.DirectorOfMovieKey;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    @EqualsAndHashCode.Exclude
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @MapsId("directorId")
    @JoinColumn(name = "dricetor_id")
    @EqualsAndHashCode.Exclude
    private Director director;
}
