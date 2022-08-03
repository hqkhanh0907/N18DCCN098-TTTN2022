package com.example.demo.model.Key;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieEvaluateKey implements Serializable {
    @Column(name = "account_id")
    int userId;

    @Column(name = "movie_id")
    int movieId;
}
