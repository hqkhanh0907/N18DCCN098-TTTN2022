package com.example.demo.service.implement;

import com.example.demo.dto.GenreDto;
import com.example.demo.dto.GenreOfMovieDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.dto.map.GenreMapper;
import com.example.demo.dto.map.GenreOfMovieMapper;
import com.example.demo.dto.map.MovieMapper;
import com.example.demo.model.GenreOfMovie;
import com.example.demo.repository.FKGenreRepository;
import com.example.demo.repository.MovieDetailRepository;
import com.example.demo.repository.MovieGenreRepository;
import com.example.demo.service.FKGenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FKGenreServiceImpl implements FKGenreService {
    private final FKGenreRepository fkGenreRepository;
    private final MovieMapper movieDetailMap;
    private final GenreMapper movieGenreMap;
    private final GenreOfMovieMapper genreOfMovieMapper;
    private final MovieGenreRepository movieGenreRepository;
    private final MovieDetailRepository movieDetailRepository;

    @Override
    public void saveGenreOfMovie(GenreOfMovie genreOfMovie) {
        try {
            fkGenreRepository.saveGenre(genreOfMovie.getId().getMovieId(), genreOfMovie.getId().getGenreId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GenreOfMovieDto> getAllFKGenre() {
        return fkGenreRepository.findAll().stream().map(genreOfMovie -> {
            return genreOfMovieMapper.genreOfMovieToGenreOfMovieDto(genreOfMovie);
        }).collect(Collectors.toList());
    }

    @Override
    public List<GenreDto> getGenreOnMovie(Integer movieId) {
        List<GenreDto> genreDtos = new ArrayList<>();
        for (GenreOfMovie genreOfMovie : fkGenreRepository.findAll()) {
            if (genreOfMovie.getId().getMovieId().equals(movieId)) {
                genreDtos.add(movieGenreMap.genreToGenreDto(genreOfMovie.getGenre()));
            }
        }
        return genreDtos;
    }

    @Override
    public List<MovieDto> getMovieOnGenre(Integer genreId) {
        List<MovieDto> movieDtos = new ArrayList<>();
        for (GenreOfMovie genreOfMovie : fkGenreRepository.findAll()) {
            if (genreOfMovie.getId().getGenreId().equals(genreId)) {
                movieDtos.add(movieDetailMap.movieToMovieDto(genreOfMovie.getMovie()));
            }
        }
        return movieDtos;
    }

    @Override
    public void deleteByGenreId(Integer genreId) {
        List<GenreOfMovie> genreOfMovies = fkGenreRepository.findAll();
        genreOfMovies.forEach(fkGenre -> {
            if (fkGenre.getGenre().getId() == genreId) {
                fkGenreRepository.delete(fkGenre);
            }
        });
    }

    @Override
    public void deleteByMovieId(Integer movieId) {
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
                fkGenreRepository.deleteGenre(genre.getId().getMovieId(), genre.getId().getGenreId());
            }
        }
    }
}
