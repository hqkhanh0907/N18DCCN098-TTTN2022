package com.example.demo.service.implement;

import com.example.demo.dto.CastDto;
import com.example.demo.dto.GenreDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.dto.MovieEvaluateDto;
import com.example.demo.dto.MovieRate;
import com.example.demo.dto.map.CastMapper;
import com.example.demo.dto.map.GenreMapper;
import com.example.demo.dto.map.MovieEvaluateMapper;
import com.example.demo.dto.map.MovieMapper;
import com.example.demo.model.Account;
import com.example.demo.model.Cast;
import com.example.demo.model.CastOfMovie;
import com.example.demo.model.Director;
import com.example.demo.model.Genre;
import com.example.demo.model.Movie;
import com.example.demo.model.MovieEvaluate;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.MovieCastRepository;
import com.example.demo.repository.MovieDetailRepository;
import com.example.demo.repository.MovieDirectorRepository;
import com.example.demo.repository.MovieGenreRepository;
import com.example.demo.service.CastOfMovieService;
import com.example.demo.service.FKDirectorService;
import com.example.demo.service.FKGenreService;
import com.example.demo.service.MovieDetailService;
import com.example.demo.service.MovieEvaluateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieDetailServiceImpl implements MovieDetailService {
    private final MovieDetailRepository movieDetailRepository;
    private final FKGenreService fkGenreService;
    private final FKDirectorService fkDirectorService;
    private final CastOfMovieService castOfMovieService;
    private final MovieEvaluateService movieEvaluateService;
    private final MovieMapper movieMapper;
    private final GenreMapper genreMapper;
    private final CastMapper castMapper;
    private final MovieEvaluateMapper movieEvaluateMapper;
    private final MovieCastRepository movieCastRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final MovieDirectorRepository movieDirectorRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<MovieDto> getAllMovie() {
        return movieDetailRepository.findAll().stream().map(movie -> {
            return movieMapper.movieToMovieDto(movie);
        }).collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(int movieId) {
        return movieMapper.movieToMovieDto(movieDetailRepository.getById(movieId));
    }

    @Override
    public MovieDto getMovieBySlug(String slug) {
        return movieMapper.movieToMovieDto(movieDetailRepository.findMovieDetailBySlug(slug));
    }

//    @Override
//    public MovieDetailPage getAllMovieDetailPage(int pageNo, int pageSize, String sortBy, String sortDir) {
//
//        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
//        // create Pageable instance
////        Pageable pageable = ;
//        Page<Movie> movieDetails = movieDetailRepository.findAll(PageRequest.of(pageNo, pageSize, sort));
//        // get content for page object
//        List<Movie> listOfPosts = movieDetails.getContent();
//
//        List<MovieDto> content = movieDetailMap.listMovieDetailToDTO(listOfPosts);
//        MovieDetailPage MovieDetailPage = new MovieDetailPage();
//        MovieDetailPage.setMovieDtoS(content);
//        MovieDetailPage.setPageNo(movieDetails.getNumber());
//        MovieDetailPage.setPageSize(movieDetails.getSize());
//        MovieDetailPage.setTotalElements(movieDetails.getTotalElements());
//        MovieDetailPage.setTotalPages(movieDetails.getTotalPages());
//        MovieDetailPage.setFirst(movieDetails.isFirst());
//        MovieDetailPage.setLast(movieDetails.isLast());
//
//        return MovieDetailPage;
//    }

    @Override
    public MovieDto addMovieDetail(Movie movie) throws Exception {
        if (checkExitNameAddMovie(movie.getName())) {
            throw new Exception("The title of the movie already exists!");
        } else {
            movieDetailRepository.save(movie);
            return movieMapper.movieToMovieDto(movie);
        }

    }

    private boolean checkExitNameAddMovie(String title) {
        List<Movie> movies = movieDetailRepository.findAll();
        for (Movie movie : movies) {
            if (movie.getName().equals(title)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MovieDto editMovieDetail(Movie movieRequest) throws Exception {
        assert movieRequest.getId() != null;
        Movie movie = movieDetailRepository.getById(movieRequest.getId());
        if (checkExitNameEditMovie(movieRequest.getName(), movie.getId())) {
            throw new Exception("The title of the movie already exists!");
        } else {
            movie.setName(movieRequest.getName());
            movie.setPoster(movieRequest.getPoster());
            movie.setDescription(movieRequest.getDescription());
            movie.setMovieStatus(movieRequest.getMovieStatus());
            movie.setLinkTrailer(movieRequest.getLinkTrailer());
            movie.setLinkMovie(movieRequest.getLinkMovie());
            movie.setReleaseDate(movieRequest.getReleaseDate());
            movie.setMovieDuration(movieRequest.getMovieDuration());
            movie.setViewNumber(movieRequest.getViewNumber());
            if (movieRequest.getMovieEvaluates() != null) {
                movie.setMovieEvaluates(movieRequest.getMovieEvaluates().stream().map(movieEvaluate -> {
                    Movie movieDetailEvaluate = movieDetailRepository.getById(movieEvaluate.getId().getMovieId());
                    Account account = accountRepository.getById(movieEvaluate.getId().getUserId());
                    MovieEvaluate newEvaluate = new MovieEvaluate();
                    newEvaluate.setMovie(movieDetailEvaluate);
                    newEvaluate.setAccount(account);
                    newEvaluate.setDate(movieEvaluate.getDate());
                    newEvaluate.setContent(movieEvaluate.getContent());
                    newEvaluate.setRate(movieEvaluate.getRate());
                    return newEvaluate;
                }).collect(Collectors.toList()));
            }
            if (movieRequest.getCastOfMovies() != null) {
                castOfMovieService.removeFkCastExits(movieRequest.getId());
                movie.setCastOfMovies(movieRequest.getCastOfMovies().stream().map(fkCast -> {
                    Movie movieCast = movieDetailRepository.getById(fkCast.getId().getMovieId());
                    Cast cast = movieCastRepository.getById(fkCast.getId().getCastId());
                    CastOfMovie newCastOfMovie = new CastOfMovie();
                    newCastOfMovie.setMovie(movieCast);
                    newCastOfMovie.setCast(cast);
                    newCastOfMovie.setCastCharacter(fkCast.getCastCharacter());
                    return newCastOfMovie;
                }).collect(Collectors.toList()));
            }
            if (movieRequest.getGenreOfMovies() != null) {
                fkGenreService.removeGenreExits(movieRequest.getId());
                movie.setGenreOfMovies(movieRequest.getGenreOfMovies().stream().map(fkGenre -> {
                    Movie movieGenre = movieDetailRepository.getById(fkGenre.getId().getMovieId());
                    Genre genre = movieGenreRepository.getById(fkGenre.getId().getGenreId());
                    fkGenre.setMovie(movieGenre);
                    fkGenre.setGenre(genre);
                    return fkGenre;
                }).collect(Collectors.toList()));
            }
            if (movieRequest.getDirectorOfMovies() != null) {
                fkDirectorService.removieDirectorExits(movieRequest.getId());
                movie.setDirectorOfMovies(movieRequest.getDirectorOfMovies().stream().map(fkDirector -> {
                    Movie movieDirector = movieDetailRepository.getById(fkDirector.getId().getMovieId());
                    Director director = movieDirectorRepository.getById(fkDirector.getId().getDirectorId());
                    fkDirector.setMovie(movieDirector);
                    fkDirector.setDirector(director);
                    return fkDirector;
                }).collect(Collectors.toList()));
            }
            movieDetailRepository.save(movie);
            return movieMapper.movieToMovieDto(movieRequest);
        }

    }

    @Override
    public Movie deleteMovieDetail(int id) throws Exception {
        Movie movie = movieDetailRepository.findById(id).orElse(null);
        if (movie == null) {
            throw new Exception("Move not found");
        } else {
            castOfMovieService.deleteFkCastByMovieId(id);
            fkDirectorService.deleteFkDirectorByMovieId(id);
            fkGenreService.deleteByMovieId(id);
            movieEvaluateService.deleteMovieEvaluateByMovieId(id);
            movieDetailRepository.delete(movie);
            return movie;
        }
    }

    @Override
    public Movie getMovieDetailByName(String name) {
        return movieDetailRepository.findMovieDetailByName(name);
    }

    @Override
    public List<MovieRate> getListMovieRate() throws Exception {
        List<MovieRate> movieRates = new ArrayList<>();
        List<Movie> movies = movieDetailRepository.findAll();
        for (Movie movie : movies) {
            if (movie.getMovieEvaluates().isEmpty()) {
                movieRates.add(new MovieRate(movie.getId(), 0.0, 0));
            } else {
                movieRates.add(getRateMovie(movie.getId()));
            }
        }
        return movieRates.stream().sorted(Comparator.comparing(MovieRate::getRate).reversed()).collect(Collectors.toList());
    }

    @Override
    public MovieRate getRateMovie(int id) throws Exception {
        Movie movie = movieDetailRepository.findById(id).orElse(null);
        if (movie != null) {
            List<MovieEvaluate> movieEvaluates = movieEvaluateService.getMovieEvaluates();
            int sumRate = 0;
            int countMovie = 0;
            assert movieEvaluates != null;
            for (MovieEvaluate movieEvaluate : movieEvaluates) {
                if (movieEvaluate.getId().getMovieId() == id) {
                    sumRate += movieEvaluate.getRate();
                    countMovie = countMovie + 1;
                }
            }
            if (countMovie != 0) {
                return new MovieRate(movie.getId(), ((double) sumRate / (double) countMovie), countMovie);
            } else {
                return new MovieRate(movie.getId(), 0.0, countMovie);
            }
        } else {
            throw new Exception("Movie not found!");
        }
    }

    @Override
    public List<GenreDto> getMovieGenres(int id) {
        Movie movie = movieDetailRepository.getById(id);
        if (movie.getGenreOfMovies() != null || movie.getGenreOfMovies().size() != 0) {
            return movie.getGenreOfMovies().stream().map(genreOfMovie -> {
                return genreMapper.genreToGenreDto(genreOfMovie.getGenre());
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<CastDto> getMovieCasts(int id) {
        Movie movieCast = movieDetailRepository.getById(id);
        if (movieCast.getCastOfMovies() != null || movieCast.getCastOfMovies().size() != 0) {
            return movieCast.getCastOfMovies().stream().map(castOfMovie -> {
                return castMapper.castToCastDto(castOfMovie.getCast());
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<MovieDto> search(String searchQuery) {
        return movieDetailRepository.findByNameLike(searchQuery).stream().map(movie -> {
            return movieMapper.movieToMovieDto(movie);
        }).collect(Collectors.toList());
    }

    @Override
    public List<MovieEvaluateDto> loadEvaluate(int movieId) {
        return movieEvaluateService.getMovieEvaluates().stream().map(movieEvaluate -> {
            if (movieEvaluate.getId().getMovieId() == movieId) {
                return movieEvaluateMapper.movieEvaluateToMovieEvaluateDto(movieEvaluate);
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<MovieEvaluateDto> loadEvaluateInAcc(int accId) {
        return movieEvaluateService.getMovieEvaluates().stream().map(movieEvaluate -> {
            if (movieEvaluate.getId().getUserId() == accId) {
                return movieEvaluateMapper.movieEvaluateToMovieEvaluateDto(movieEvaluate);
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public MovieEvaluateDto saveEvaluate(MovieEvaluateDto movieEvaluateDTO) {
        Movie movie = movieDetailRepository.getById(movieEvaluateDTO.getId().getMovieId());
        MovieEvaluate movieEvaluate = movieEvaluateMapper.movieEvaluateDtoToMovieEvaluate(movieEvaluateDTO);
        List<MovieEvaluate> movieEvaluates = new ArrayList<>();
        if (movie.getMovieEvaluates() != null) {
            movieEvaluates = movie.getMovieEvaluates();
            for (MovieEvaluate movieEvaluateCheck : movieEvaluates) {
                if ((movieEvaluateCheck.getId().getUserId() == movieEvaluate.getId().getUserId()) &&
                        (movieEvaluateCheck.getId().getMovieId() == movieEvaluate.getId().getMovieId())) {
                    movieEvaluateService.editEvaluate(movieEvaluate);
                    return movieEvaluateDTO;
                }
            }
            movieEvaluates.add(movieEvaluate);
        } else {
            movieEvaluates.add(movieEvaluate);
        }
        movie.setMovieEvaluates(movieEvaluates);
        movieDetailRepository.save(movie);
        return movieEvaluateDTO;
    }

    public boolean checkExitNameEditMovie(String title, int id) {
        List<Movie> movies = movieDetailRepository.findAll();
        for (Movie movie : movies) {
            if (movie.getName().equals(title) && (id != movie.getId())) {
                return true;
            }
        }
        return false;
    }
}
