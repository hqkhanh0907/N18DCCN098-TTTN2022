package com.example.demo.model;

import com.example.demo.model.Key.FavoriteMovieKey;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "favorite_movie")
public class FavoriteMovie {
    @EmbeddedId
    private FavoriteMovieKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    @EqualsAndHashCode.Exclude
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    @EqualsAndHashCode.Exclude
    private Movie movie;

    @Column(name = "date")
    private Date date;


}
