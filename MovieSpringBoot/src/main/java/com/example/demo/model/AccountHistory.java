package com.example.demo.model;

import com.example.demo.model.Key.AccountHistoryKey;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_history")
public class AccountHistory {
    @EmbeddedId
    private AccountHistoryKey accountHistoryKey;

    @ManyToOne(cascade = {CascadeType.ALL})
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    @EqualsAndHashCode.Exclude
    private Account account;

    @ManyToOne(cascade = {CascadeType.ALL})
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    @EqualsAndHashCode.Exclude
    private Movie movie;

    @Column(name = "date")
    private Date date;

    @Column(name = "time_watched")
    private Float time_watched;


}
