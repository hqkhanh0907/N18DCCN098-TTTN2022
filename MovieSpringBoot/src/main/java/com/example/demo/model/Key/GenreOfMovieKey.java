package com.example.demo.model.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.kaczmarzyk.spring.data.jpa.domain.In;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreOfMovieKey implements Serializable {

    @Column(name = "genre_id")
    Integer genreId;

    @Column(name = "movie_id")
    Integer movieId;
}
