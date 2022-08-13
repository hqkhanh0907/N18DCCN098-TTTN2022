package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CastPage {
    private List<CastDto> movieCastDTOS;
    private Integer pageNo;
    private Integer pageSize;
    private long totalElements;
    private Integer totalPages;
    private boolean first;
    private boolean last;
}
