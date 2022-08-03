package com.example.demo.service.implement;

import com.example.demo.dto.GenreDto;
import com.example.demo.dto.GenreOfMovieDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.dto.map.GenreMapper;
import com.example.demo.dto.map.GenreOfMovieMapper;
import com.example.demo.dto.map.MovieMapper;
import com.example.demo.model.GenreOfMovie;
import com.example.demo.repository.FKGenreRepository;
import com.example.demo.service.FKGenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FKGenreServiceImpl implements FKGenreService {
    private final FKGenreRepository fkGenreRepository;
    private final MovieMapper movieDetailMap;
    private final GenreMapper movieGenreMap;
    private final GenreOfMovieMapper genreOfMovieMapper;

    @Override
    public List<GenreOfMovieDto> getAllFKGenre() {
        return fkGenreRepository.findAll().stream().map(genreOfMovie -> {
            return genreOfMovieMapper.genreOfMovieToGenreOfMovieDto(genreOfMovie);
        }).collect(Collectors.toList());
    }

    @Override
    public List<GenreDto> getGenreOnMovie(int movieId) {
        return fkGenreRepository.findAll().stream().map(genreOfMovie -> {
            if (genreOfMovie.getMovie().getId().equals(movieId)) {
                return movieGenreMap.genreToGenreDto(genreOfMovie.getGenre());
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getMovieOnGenre(int genreId) {
        return fkGenreRepository.findAll().stream().map(genreOfMovie -> {
            if (genreOfMovie.getGenre().getId().equals(genreId)) {
                return movieDetailMap.movieToMovieDto(genreOfMovie.getMovie());
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteByGenreId(int genreId) {
        List<GenreOfMovie> genreOfMovies = fkGenreRepository.findAll();
        genreOfMovies.forEach(fkGenre -> {
            if (fkGenre.getGenre().getId() == genreId) {
                fkGenreRepository.delete(fkGenre);
            }
        });
    }

    @Override
    public void deleteByMovieId(int movieId) {
        List<GenreOfMovie> genreOfMovies = fkGenreRepository.findAll();
        genreOfMovies.forEach(fkGenre -> {
            if (fkGenre.getMovie().getId() == movieId) {
                fkGenreRepository.delete(fkGenre);
            }
        });
    }

    @Override
    public void removeGenreExits(Integer movieId) {
        List<GenreOfMovie> genresPresentOfMovie = fkGenreRepository.findAll();
            for (GenreOfMovie genre : genresPresentOfMovie) {
                if (genre.getId().getMovieId() == movieId) {
                    fkGenreRepository.delete(genre);
                }
            }
        }
    }
