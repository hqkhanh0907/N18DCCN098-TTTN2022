package com.example.demo.service.implement;

import com.example.demo.dto.DirectorDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.dto.map.DirectorMapper;
import com.example.demo.dto.map.MovieMapper;
import com.example.demo.model.DirectorOfMovie;
import com.example.demo.repository.FKDirectorRepository;
import com.example.demo.service.FKDirectorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FKDirectorServiceImpl implements FKDirectorService {
    private final FKDirectorRepository fkDirectorRepository;
    private final MovieMapper movieDetailMap;
    private final DirectorMapper movieDirectorMap;

    @Override
    public List<DirectorDto> getDirectorByMovieId(int movieDetailId) {
        return fkDirectorRepository.findAll().stream().map(directorOfMovie -> {
            if (directorOfMovie.getMovie().getId().equals(movieDetailId)) {
                return movieDirectorMap.directorToDirectorDto(directorOfMovie.getDirector());
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getMovieDetailByDirectorId(int directorId) {
        return fkDirectorRepository.findAll().stream().map(directorOfMovie -> {
            if (directorOfMovie.getDirector().getId().equals(directorId)) {
                return movieDetailMap.movieToMovieDto(directorOfMovie.getMovie());
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteFkDirectorByDirectorId(int directorId) {
        List<DirectorOfMovie> directorOfMovies = fkDirectorRepository.findAll();
        directorOfMovies.forEach(fkDirector -> {
            if (fkDirector.getDirector().getId() == directorId) {
                fkDirectorRepository.delete(fkDirector);
            }
        });
    }

    @Override
    public void deleteFkDirectorByMovieId(int movieDetailId) {
        List<DirectorOfMovie> directorOfMovies = fkDirectorRepository.findAll();
        directorOfMovies.forEach(fkDirector -> {
            if (fkDirector.getMovie().getId() == movieDetailId) {
                fkDirectorRepository.delete(fkDirector);
            }
        });
    }

    @Override
    public void removieDirectorExits(Integer movieId) {
        List<DirectorOfMovie> directorsPresentOfMovie = fkDirectorRepository.findAll();
        for (DirectorOfMovie director : directorsPresentOfMovie) {
            if (director.getId().getMovieId() == movieId) {
                fkDirectorRepository.delete(director);
            }
        }
    }

}
