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
public class CastOfMovieKey implements Serializable {

    @Column(name = "cast_id")
    Integer castId;

    @Column(name = "movie_id")
    Integer movieId;
}
