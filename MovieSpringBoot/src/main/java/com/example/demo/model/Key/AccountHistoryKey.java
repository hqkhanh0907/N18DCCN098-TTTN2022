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
public class AccountHistoryKey implements Serializable {
    @Column(name = "account_id", nullable = false)
    Integer accountId;

    @Column(name = "movie_id", nullable = false)
    Integer movieId;
}