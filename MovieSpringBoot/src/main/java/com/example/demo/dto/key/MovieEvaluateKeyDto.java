package com.example.demo.dto.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieEvaluateKeyDto implements Serializable {
    private int userId;
    private int movieId;
}
