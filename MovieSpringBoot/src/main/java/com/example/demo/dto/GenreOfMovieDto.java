package com.example.demo.dto;

import com.example.demo.dto.key.GenreOfMovieKeyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreOfMovieDto implements Serializable {
    private GenreOfMovieKeyDto id = new GenreOfMovieKeyDto();
}
