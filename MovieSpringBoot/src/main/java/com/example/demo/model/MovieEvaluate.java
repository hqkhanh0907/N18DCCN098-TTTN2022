package com.example.demo.model;

import com.example.demo.model.Key.MovieEvaluateKey;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "account_id")
    @EqualsAndHashCode.Exclude
    private Account account = new Account();


    @ManyToOne(fetch = FetchType.LAZY)
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
