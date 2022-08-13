package com.example.demo.model;

import com.example.demo.model.Key.MovieEvaluateKey;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie_evaluate")
public class MovieEvaluate {
    @EmbeddedId
    private MovieEvaluateKey id = new MovieEvaluateKey();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @MapsId("userId")
    @JoinColumn(name = "account_id")
    @EqualsAndHashCode.Exclude
    private Account account = new Account();


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    @EqualsAndHashCode.Exclude
    private Movie movie = new Movie();

    @NotNull
    @Column(name = "rate")
    private Integer rate;

    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotNull
    @Column(name = "status")
    private Integer status;

}
