package com.example.demo.service.implement;

import com.example.demo.dto.GenreDto;
import com.example.demo.dto.map.GenreMapper;
import com.example.demo.model.Genre;
import com.example.demo.model.GenreOfMovie;
import com.example.demo.repository.FKGenreRepository;
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
    private final FKGenreRepository fkGenreRepository;

    @Override
    public List<GenreDto> getAllMovieGen() {
        return movieGenreRepository.findAll().stream().map(genre -> {
            return genreMapper.genreToGenreDto(genre);
        }).collect(Collectors.toList());
    }

    @Override
    public GenreDto getMovieGenreById(Integer id) {
        return genreMapper.genreToGenreDto(movieGenreRepository.getById(id));
    }

    @Override
    public Boolean deleteMovieGenreById(Integer id) {
        Genre genre = movieGenreRepository.findById(id).orElse(null);
        if (genre == null) {
            throw new RuntimeException("Not found genre");
        } else {
            if (!checkGenreOnFKGenre(id)) {
                movieGenreRepository.deleteGenre(genre.getId());
                return true;
            } else {
                throw new RuntimeException("Already exist in movie! Couldn't delete genre!");
            }
        }
    }
    private Boolean checkGenreOnFKGenre(Integer genreId) {
        List<GenreOfMovie> genreOfMovies = fkGenreRepository.findAll();
        for (GenreOfMovie genreOfMovie : genreOfMovies) {
            if (genreOfMovie.getId().getGenreId() == genreId) {
                return true;
            }
        }
        return false;
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
    public Boolean editMovieGenre(GenreDto genreDto) {
        Genre genre = movieGenreRepository.findById(genreDto.getId()).orElse(null);
        if (genre == null) {
            throw new RuntimeException("Not found genre");
        } else {
            if (checkGenreName(genreDto.getName()) == false) {
                genre.setName(genreDto.getName());
                movieGenreRepository.save(genre);
                return true;
            }
        }
        return false;
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
