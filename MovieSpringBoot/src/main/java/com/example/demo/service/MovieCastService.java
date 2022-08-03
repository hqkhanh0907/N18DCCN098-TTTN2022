package com.example.demo.service;

import com.example.demo.dto.CastDto;
import com.example.demo.dto.CastPage;

import java.util.List;

public interface MovieCastService {

    List<CastDto> getAllCast();

    CastDto getMovieCastById(int id);

    String deleteMovieCastById(int id);

    CastDto createMovieCast(CastDto castDto);

    String editMovieCast(CastDto castDto);

    CastPage getAllCastPage(int pageNo, int pageSize, String sortBy, String sortDir);
}
