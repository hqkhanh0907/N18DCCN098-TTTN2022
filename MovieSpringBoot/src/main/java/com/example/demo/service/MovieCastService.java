package com.example.demo.service;

import com.example.demo.dto.CastDto;
import com.example.demo.dto.CastPage;

import java.util.List;

public interface MovieCastService {

    List<CastDto> getAllCast();

    CastDto getMovieCastById(Integer id);

    Boolean deleteMovieCastById(Integer id);

    CastDto createMovieCast(CastDto castDto);

    Boolean editMovieCast(CastDto castDto);

    CastPage getAllCastPage(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    Integer getNumCast();
}
