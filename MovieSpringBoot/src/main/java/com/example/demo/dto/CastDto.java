package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CastDto implements Serializable {
    private Integer id;
    @NotNull(message = "Cast's avatar cannot be empty")
    private String avatar;
    @NotBlank(message = "Cast's name cannot be empty")
    private String name;
    private String story;
    @NotNull(message = "Cast's birthday cannot be empty")
    private Date birthday;
    private List<CastOfMovieDto> castOfMovies = new ArrayList<>();
}
