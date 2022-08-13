package com.example.demo.dto;

import com.example.demo.dto.key.FavoriteMovieKeyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteMovieDto implements Serializable {
    private FavoriteMovieKeyDto id;
    private AccountDto account;
    private MovieDto movie;
    private Date date;
}
