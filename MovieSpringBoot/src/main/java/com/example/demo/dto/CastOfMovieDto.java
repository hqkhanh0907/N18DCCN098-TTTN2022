package com.example.demo.dto;

import com.example.demo.dto.key.CastOfMovieKeyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CastOfMovieDto implements Serializable {
    private CastOfMovieKeyDto id = new CastOfMovieKeyDto();
    private String castCharacter;
}
