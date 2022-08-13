package com.example.demo.service.implement;

import com.example.demo.dto.CastOfMovieDto;
import com.example.demo.dto.map.CastMapper;
import com.example.demo.dto.map.CastOfMovieMapper;
import com.example.demo.dto.map.MovieMapper;
import com.example.demo.model.CastOfMovie;
import com.example.demo.repository.FKCastRepository;
import com.example.demo.service.CastOfMovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CastOfMovieServiceImpl implements CastOfMovieService {
    private final FKCastRepository fkCastRepository;
    private final CastOfMovieMapper castOfMovieMapper;
    private final CastMapper castMapper;
    private final MovieMapper movieDetailMap;

    @Override
    public List<CastOfMovieDto> getCastByMovieId(Integer movieId) {
        List<CastOfMovieDto> castOfMovieDtos = new ArrayList<>();
        for (CastOfMovie castOfMovie : fkCastRepository.findAll()) {
            if (castOfMovie.getId().getMovieId().equals(movieId)) {
                castOfMovieDtos.add(castOfMovieMapper.castOfMovieToCastOfMovieDto(castOfMovie));
            }
        }
        return castOfMovieDtos;
    }

    @Override
    public List<CastOfMovieDto> getMovieDetailByCastId(Integer catsId) {
        List<CastOfMovieDto> castOfMovieDtos = new ArrayList<>();
        for (CastOfMovie castOfMovie : fkCastRepository.findAll()) {
            if (castOfMovie.getId().getCastId().equals(catsId)) {
                castOfMovieDtos.add(castOfMovieMapper.castOfMovieToCastOfMovieDto(castOfMovie));
            }
        }
        return castOfMovieDtos;
    }

    @Override
    public void deleteFkCastByCastId(Integer castId) {
        List<CastOfMovie> castOfMovies = fkCastRepository.findAll();
        castOfMovies.forEach(fkCast -> {
            if (fkCast.getCast().getId().equals(castId)) {
                fkCastRepository.delete(fkCast);
            }
        });
    }

    @Override
    public Boolean deleteFkCastByMovieId(Integer movieDetailId) {
        List<CastOfMovie> castOfMovies = fkCastRepository.findAll();
        castOfMovies.forEach(fkCast -> {
            if (fkCast.getMovie().getId().equals(movieDetailId)) {
                fkCastRepository.deleteById(fkCast.getId());
            }
        });
        return true;
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
                fkCastRepository.deleteCastOfMovie(cast.getId().getMovieId(), cast.getId().getCastId());
            }
        }
    }

    @Override
    public void saveCastOfMovie(CastOfMovie castOfMovie) {
        try {
            fkCastRepository.saveCastOfMovie(castOfMovie.getId().getMovieId(), castOfMovie.getId().getCastId(), castOfMovie.getCastCharacter());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
