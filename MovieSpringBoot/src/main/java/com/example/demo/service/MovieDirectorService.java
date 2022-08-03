package com.example.demo.service;

import com.example.demo.dto.DirectorDto;
import com.example.demo.dto.DirectorPage;

import java.util.List;

public interface MovieDirectorService {
    List<DirectorDto> getAllMovieDirector();

    DirectorDto getMovieDirectorById(int id);

    String deleteMovieDirectorById(int id);

    DirectorDto createMovieDirector(DirectorDto movieDirectorDTO);

    String editMovieDirector(DirectorDto movieDirectorDTO);

    DirectorPage getAllDirectorPage(int pageNo, int pageSize, String sortBy, String sortDir);

}
