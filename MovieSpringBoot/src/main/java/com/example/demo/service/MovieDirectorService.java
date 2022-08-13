package com.example.demo.service;

import com.example.demo.dto.DirectorDto;
import com.example.demo.dto.DirectorPage;

import java.util.List;

public interface MovieDirectorService {
    List<DirectorDto> getAllMovieDirector();

    DirectorDto getMovieDirectorById(Integer id);

    String deleteMovieDirectorById(Integer id);

    DirectorDto createMovieDirector(DirectorDto movieDirectorDTO);

    String editMovieDirector(DirectorDto movieDirectorDTO);

    DirectorPage getAllDirectorPage(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

}
