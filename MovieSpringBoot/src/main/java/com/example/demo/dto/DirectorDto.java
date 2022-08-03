package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectorDto implements Serializable {
    private int id;
    @NotBlank(message = "Director's avatar cannot be empty")
    private String avatar;
    @NotBlank(message = "Director's name cannot be empty")
    private String name;
    private String story;
    @NotNull(message = "Director's birthday cannot be empty")
    private Date birthday;
}
