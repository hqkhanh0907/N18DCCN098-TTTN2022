package com.example.demo.model.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteMovieKey implements Serializable {
    @Column(name = "account_id")
    Integer accountId;

    @Column(name = "movie_id")
    Integer movieId;
}