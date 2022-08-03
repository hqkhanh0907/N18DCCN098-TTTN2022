package com.example.demo.service.implement;

import com.example.demo.dto.CastDto;
import com.example.demo.dto.CastOfMovieDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.dto.map.CastMapper;
import com.example.demo.dto.map.CastOfMovieMapper;
import com.example.demo.dto.map.MovieMapper;
import com.example.demo.model.CastOfMovie;
import com.example.demo.repository.FKCastRepository;
import com.example.demo.service.CastOfMovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CastOfMovieServiceImpl implements CastOfMovieService {
    private final FKCastRepository fkCastRepository;
    private final CastOfMovieMapper castOfMovieMapper;
    private final CastMapper castMapper;
    private final MovieMapper movieMapper;

    @Override
    public List<CastDto> getCastByMovieId(Integer id) {
        return fkCastRepository.findAll().stream().map(castOfMovie -> {
            if (castOfMovie.getId().getMovieId().equals(id)) {
                return castMapper.castToCastDto(castOfMovie.getCast());
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getMovieDetailByCastId(Integer id) {
        return fkCastRepository.findAll().stream().map(castOfMovie -> {
            return (castOfMovie.getId().getCastId().equals(id)) ? movieMapper.movieToMovieDto(castOfMovie.getMovie()) : null;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteFkCastByCastId(int castId) {
        List<CastOfMovie> castOfMovies = fkCastRepository.findAll();
        castOfMovies.forEach(fkCast -> {
            if (fkCast.getCast().getId() == castId) {
                fkCastRepository.delete(fkCast);
            }
        });
    }

    @Override
    public void deleteFkCastByMovieId(int movieDetailId) {
        List<CastOfMovie> castOfMovies = fkCastRepository.findAll();
        castOfMovies.forEach(fkCast -> {
            if (fkCast.getMovie().getId() == movieDetailId) {
                fkCastRepository.delete(fkCast);
            }
        });
    }

    @Override
    public List<CastOfMovieDto> getAllFKCast() {
        return fkCastRepository.findAll().stream().map(castOfMovie -> {
            return castOfMovieMapper.castOfMovieToCastOfMovieDto(castOfMovie);
        }).collect(Collectors.toList());
    }

    @Override
    public void removeFkCastExits(Integer movieId) {
        List<CastOfMovie> castsPresentOfMovie = fkCastRepository.findAll();
        for (CastOfMovie cast : castsPresentOfMovie) {
            if (cast.getId().getMovieId() == movieId) {
                fkCastRepository.delete(cast);
            }
        }
    }
}
