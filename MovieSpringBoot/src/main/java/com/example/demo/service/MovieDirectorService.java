package com.example.demo.service;

import com.example.demo.dto.DirectorDto;
import com.example.demo.dto.DirectorPage;

import java.util.List;

public interface MovieDirectorService {
    List<DirectorDto> getAllMovieDirector();

    DirectorDto getMovieDirectorById(Integer id);

    Boolean deleteMovieDirectorById(Integer id);

    DirectorDto createMovieDirector(DirectorDto movieDirectorDTO);

    Boolean editMovieDirector(DirectorDto movieDirectorDTO);

    DirectorPage getAllDirectorPage(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

}
