package com.example.demo.service.implement;

import com.example.demo.dto.key.FavoriteMovieKeyDto;
import com.example.demo.model.FavoriteMovie;
import com.example.demo.model.Key.FavoriteMovieKey;
import com.example.demo.repository.FavoriteMovieRepository;
import com.example.demo.service.FavoriteMovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FavoriteMovieServiceImpl implements FavoriteMovieService {
    private final FavoriteMovieRepository favoriteMovieRepository;

    @Override
    public Boolean unFollow(FavoriteMovieKeyDto favoriteMovieKeyDto) {
        FavoriteMovie favoriteMovie;
        try {
            //new key
            FavoriteMovieKey favoriteMovieKey = new FavoriteMovieKey();
            favoriteMovieKey.setMovieId(favoriteMovieKeyDto.getMovieId());
            favoriteMovieKey.setAccountId(favoriteMovieKeyDto.getAccountId());
            favoriteMovie = favoriteMovieRepository.findById(favoriteMovieKey).orElse(null);
            if (Objects.isNull(favoriteMovie)) {
                throw new RuntimeException("Not found favoriteMovie");
            }
            favoriteMovieRepository.delete(favoriteMovie);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFavByAccount(Integer id) {
        List<FavoriteMovie> favoriteMovies = favoriteMovieRepository.findAll();
        for (FavoriteMovie favoriteMovie: favoriteMovies) {
            if (favoriteMovie.getId().getAccountId() == id) {
                favoriteMovieRepository.deleteFavorte(favoriteMovie.getId().getAccountId(), favoriteMovie.getId().getMovieId());
            }
        }
    }
}
