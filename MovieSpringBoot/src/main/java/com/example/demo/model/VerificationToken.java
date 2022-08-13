package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "account_id")
    @EqualsAndHashCode.Exclude
    private Account account = new Account();

    @Column(name = "create_time")
    private Instant createdTime = Instant.now();

    @Column(name = "token_content")
    @NotBlank(message = "Token content cannot be empty")
    private String tokenContent;
}
