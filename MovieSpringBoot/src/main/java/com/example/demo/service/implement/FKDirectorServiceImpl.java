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

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FKDirectorServiceImpl implements FKDirectorService {
    private final FKDirectorRepository fkDirectorRepository;
    private final MovieMapper movieDetailMap;
    private final DirectorMapper movieDirectorMap;

    @Override
    public List<DirectorDto> getDirectorByMovieId(Integer movieDetailId) {
        List<DirectorDto> directorDtos = new ArrayList<>();
        for(DirectorOfMovie directorOfMovie: fkDirectorRepository.findAll()) {
            if (directorOfMovie.getId().getMovieId().equals(movieDetailId)) {
                directorDtos.add(movieDirectorMap.directorToDirectorDto(directorOfMovie.getDirector()));
            }
        }
        return directorDtos;
    }

    @Override
    public List<MovieDto> getMovieDetailByDirectorId(Integer directorId) {
        List<MovieDto> movieDtos = new ArrayList<>();
        for(DirectorOfMovie directorOfMovie: fkDirectorRepository.findAll()) {
            if (directorOfMovie.getId().getDirectorId().equals(directorId)) {
                movieDtos.add(movieDetailMap.movieToMovieDto(directorOfMovie.getMovie()));
            }
        }
        return movieDtos;
    }

    @Override
    public void deleteFkDirectorByDirectorId(Integer directorId) {
        List<DirectorOfMovie> directorOfMovies = fkDirectorRepository.findAll();
        directorOfMovies.forEach(fkDirector -> {
            if (fkDirector.getDirector().getId() == directorId) {
                fkDirectorRepository.delete(fkDirector);
            }
        });
    }

    @Override
    public void deleteFkDirectorByMovieId(Integer movieDetailId) {
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
                fkDirectorRepository.deleteDirector(director.getId().getMovieId(), director.getId().getDirectorId());
            }
        }
    }

    @Override
    public void saveDirectorOfMovie(DirectorOfMovie director) {
        try {
            fkDirectorRepository.saveDirector(director.getId().getMovieId(), director.getId().getDirectorId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
