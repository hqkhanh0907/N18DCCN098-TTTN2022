package com.example.demo.service.implement;

import com.example.demo.dto.GenreDto;
import com.example.demo.dto.map.GenreMapper;
import com.example.demo.model.Genre;
import com.example.demo.repository.MovieGenreRepository;
import com.example.demo.service.FKGenreService;
import com.example.demo.service.MovieGenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieGenreServiceImpl implements MovieGenreService {
    private final MovieGenreRepository movieGenreRepository;
    private final GenreMapper genreMapper;
    private final FKGenreService fkGenreService;

    @Override
    public List<GenreDto> getAllMovieGen() {
        return movieGenreRepository.findAll().stream().map(genre -> {
            return genreMapper.genreToGenreDto(genre);
        }).collect(Collectors.toList());
    }

    @Override
    public GenreDto getMovieGenreById(int id) {
        return genreMapper.genreToGenreDto(movieGenreRepository.getById(id));
    }

    @Override
    public String deleteMovieGenreById(int id) {
        Genre genre = movieGenreRepository.findById(id).orElse(null);
        if (genre == null) {
            throw new RuntimeException("Not found genre");
        } else {
            fkGenreService.deleteByGenreId(id);
            movieGenreRepository.delete(genre);
            return "Delete genre successfully";
        }
    }

    @Override
    public GenreDto createMovieGenre(GenreDto movieGenreDTO) {
        Genre genre = new Genre();
        if (checkGenreName(movieGenreDTO.getName()) == false) {
            genre.setName(movieGenreDTO.getName());
            movieGenreRepository.save(genre);
            return movieGenreDTO;
        }
        return null;
    }

    @Override
    public String editMovieGenre(GenreDto movieGenreDTO) {
        Genre genre = movieGenreRepository.findById(movieGenreDTO.getId()).orElse(null);
        if (genre == null) {
            throw new RuntimeException("Not found genre");
        } else {
            if (checkGenreName(movieGenreDTO.getName()) == false) {
                genre.setName(movieGenreDTO.getName());
                movieGenreRepository.save(genre);
                return "Edit genre successfully";
            }
        }
        return "Fail";
    }

    public boolean checkGenreName(String name) {
        List<Genre> genres = movieGenreRepository.findAll();
        genres.forEach(movieGenre -> {
            if (movieGenre.getName().equals(name)) {
                throw new RuntimeException("Genre name exit with " + name);
            }
        });
        return false;
    }
}
