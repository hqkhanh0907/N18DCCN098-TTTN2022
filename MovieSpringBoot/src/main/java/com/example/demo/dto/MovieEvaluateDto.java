package com.example.demo.dto;

import com.example.demo.dto.key.MovieEvaluateKeyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieEvaluateDto implements Serializable {
    private MovieEvaluateKeyDto id = new MovieEvaluateKeyDto();
    @NotNull
    private Integer rate;
    private String content;
    @NotNull
    private Date date;
    @NotNull
    private Integer status;
}
