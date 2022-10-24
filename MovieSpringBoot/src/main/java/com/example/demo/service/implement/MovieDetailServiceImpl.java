package com.example.demo.service.implement;

import com.example.demo.dto.*;
import com.example.demo.dto.map.*;
import com.example.demo.model.*;
import com.example.demo.model.Key.MovieEvaluateKey;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieDetailServiceImpl implements MovieDetailService {
    private final MovieDetailRepository movieDetailRepository;
    private final FKGenreService fkGenreService;
    private final FKDirectorService fkDirectorService;
    private final CastOfMovieService castOfMovieService;
    private final MovieEvaluateService movieEvaluateService;
    private final MovieEvaluateRepository movieEvaluateRepository;
    private final MovieMapper movieMapper;
    private final GenreMapper genreMapper;
    private final CastMapper castMapper;
    private final DirectorMapper directorMapper;
    private final MovieEvaluateMapper movieEvaluateMapper;
    private final MovieCastRepository movieCastRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final MovieDirectorRepository movieDirectorRepository;
    private final AccountRepository accountRepository;
    private final PromotionMapper promotionMapper;
    private final PromotionRepository promotionRepository;
    private final FKCastRepository fkCastRepository;
    private final GenreOfMovieMapper genreOfMovieMapper;

    @Override
    public List<MovieDto> getAllMovie() {
        return movieDetailRepository.findAll().stream().map(movie -> {
            return movieMapper.movieToMovieDto(movie);
        }).collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(Integer movieId) {
        return movieMapper.movieToMovieDto(movieDetailRepository.findById(movieId).orElse(null));
    }

    @Override
    public MovieDto getMovieBySlug(String slug) {
        return movieMapper.movieToMovieDto(movieDetailRepository.findMovieDetailBySlug(slug));
    }

//    @Override
//    public MovieDetailPage getAllMovieDetailPage(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
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
        Movie movie = movieDetailRepository.findById(movieRequest.getId()).orElse(null);
        if (checkExitNameEditMovie(movieRequest.getName(), movie.getId())) {
            throw new Exception("The title of the movie already exists!");
        } else {
            if (!movie.getCastOfMovies().isEmpty() && !Objects.isNull(movie.getCastOfMovies())) {
                castOfMovieService.removeFkCastExits(movie.getId());
            }
            if (!Objects.isNull(movie.getDirectorOfMovies()) && !movie.getDirectorOfMovies().isEmpty()) {
                fkDirectorService.removieDirectorExits(movie.getId());
            }
            if (!movie.getGenreOfMovies().isEmpty() && !Objects.isNull(movie.getGenreOfMovies())) {
                fkGenreService.removeGenreExits(movie.getId());
            }
            movie.setName(movieRequest.getName());
            movie.setPoster(movieRequest.getPoster());
            movie.setImage_showing(movieRequest.getImage_showing());
            movie.setSlug(movieRequest.getSlug());
            movie.setMoviePrice(movieRequest.getMoviePrice());
            movie.setDescription(movieRequest.getDescription());
            movie.setMovieStatus(movieRequest.getMovieStatus());
            movie.setCountryCode(movieRequest.getCountryCode());
            movie.setTranslationStatus(movieRequest.getTranslationStatus());
            movie.setLinkTrailer(movieRequest.getLinkTrailer());
            movie.setLinkMovie(movieRequest.getLinkMovie());
            movie.setReleaseDate(movieRequest.getReleaseDate());
            movie.setMovieDuration(movieRequest.getMovieDuration());
            if (!Objects.isNull(movieRequest.getCastOfMovies())) {
                for (CastOfMovie castOfMovie: movieRequest.getCastOfMovies()) {
                    castOfMovieService.saveCastOfMovie(castOfMovie);
                }
            }
            if (!Objects.isNull(movieRequest.getGenreOfMovies())) {
                for(GenreOfMovie genreOfMovie: movieRequest.getGenreOfMovies()){
                    fkGenreService.saveGenreOfMovie(genreOfMovie);
                }
            }
            if (!Objects.isNull(movieRequest.getDirectorOfMovies())) {
                for (DirectorOfMovie director: movieRequest.getDirectorOfMovies()) {
                    fkDirectorService.saveDirectorOfMovie(director);
                }
            }
            try {
                movieDetailRepository.save(movie);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return movieMapper.movieToMovieDto(movieRequest);
        }
    }

    @Override
    public Movie deleteMovieDetail(Integer id) throws Exception {
        Movie movie = movieDetailRepository.findById(id).orElse(null);
        if (movie == null) {
            throw new Exception("Move not found");
        } else {
            if (!checkRelationshipMovie(movie)) {
                throw new Exception("Movie can't delete");
            } else {
                try {
                    movieDetailRepository.deleteById(movie.getId());
                    return movie;
                } catch (Exception e) {
                    throw new Exception(e);
                }
            }
        }
    }

    private Boolean checkRelationshipMovie(Movie movie) {
        if ((Objects.isNull(movie.getGenreOfMovies()) || movie.getGenreOfMovies().isEmpty()) &&
                (Objects.isNull(movie.getDirectorOfMovies()) || movie.getDirectorOfMovies().isEmpty()) &&
                (Objects.isNull(movie.getMovieEvaluates()) || movie.getDirectorOfMovies().isEmpty()) &&
                (Objects.isNull(movie.getFavoriteMovies()) || movie.getFavoriteMovies().isEmpty()) &&
                (Objects.isNull(movie.getAccountHistories()) || movie.getAccountHistories().isEmpty()) &&
                (Objects.isNull(movie.getDirectorOfMovies()) || movie.getDirectorOfMovies().isEmpty()) &&
                (Objects.isNull(movie.getBillingInformations()) || movie.getBillingInformations().isEmpty())
        ) {
            return true;
        } else {
            return false;
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
    public MovieRate getRateMovie(Integer id) throws Exception {
        Movie movie = movieDetailRepository.findById(id).orElse(null);
        if (movie != null) {
            List<MovieEvaluate> movieEvaluates = movieEvaluateService.getMovieEvaluates();
            Integer sumRate = 0;
            Integer countMovie = 0;
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
    public List<GenreDto> getMovieGenres(Integer id) {
        Movie movie = movieDetailRepository.findById(id).orElse(null);
        if (movie.getGenreOfMovies() != null || movie.getGenreOfMovies().size() != 0) {
            return movie.getGenreOfMovies().stream().map(genreOfMovie -> {
                return genreMapper.genreToGenreDto(genreOfMovie.getGenre());
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<CastDto> getMovieCasts(Integer id) {
        Movie movieCast = movieDetailRepository.findById(id).orElse(null);
        if (movieCast.getCastOfMovies() != null || movieCast.getCastOfMovies().size() != 0) {
            return movieCast.getCastOfMovies().stream().map(castOfMovie -> {
                return castMapper.castToCastDto(castOfMovie.getCast());
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<DirectorDto> getMovieDirectors(Integer id) {
        Movie movieDirector = movieDetailRepository.findById(id).orElse(null);
        if (!Objects.isNull(movieDirector.getDirectorOfMovies()) && !movieDirector.getDirectorOfMovies().isEmpty()) {
            return movieDirector.getDirectorOfMovies().stream().map(directorOfMovie -> {
                return directorMapper.directorToDirectorDto(directorOfMovie.getDirector());
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<MovieDto> search(String searchQuery) {
        return movieDetailRepository.findMovieByName(searchQuery).stream().map(movie -> {
            return movieMapper.movieToMovieDto(movie);
        }).collect(Collectors.toList());
    }

    @Override
    public List<MovieEvaluateDto> loadEvaluate(Integer movieId) {
        List<MovieEvaluate> movieEvaluates = movieEvaluateService
                .getMovieEvaluates()
                .parallelStream()
                .filter(movieEvaluate ->
                        movieEvaluate.getId().getMovieId() == movieId
                ).collect(Collectors.toList())
                .stream()
                .sorted(
                        Comparator.comparing(MovieEvaluate::getDate)
                                .reversed())
                .collect(Collectors.toList());
        return movieEvaluates.stream().map(movieEvaluate -> {
            return movieEvaluateMapper.movieEvaluateToMovieEvaluateDto(movieEvaluate);
        }).collect(Collectors.toList());
    }

    @Override
    public List<MovieEvaluateDto> loadEvaluateInAcc(Integer accId) {
        return movieEvaluateService.getMovieEvaluates().stream().map(movieEvaluate -> {
            if (movieEvaluate.getId().getUserId() == accId) {
                return movieEvaluateMapper.movieEvaluateToMovieEvaluateDto(movieEvaluate);
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean saveEvaluate(MovieEvaluateDto movieEvaluateDTO) {
        try {
            MovieEvaluateKey movieEvaluateKey = new MovieEvaluateKey(movieEvaluateDTO.getId().getUserId(), movieEvaluateDTO.getId().getMovieId());
            MovieEvaluate movieEvaluate = movieEvaluateRepository.findById(movieEvaluateKey).orElse(null);
            if (Objects.isNull(movieEvaluate)) {
                Account account = accountRepository.findById(movieEvaluateDTO.getId().getUserId()).orElse(null);
                Movie movie = movieDetailRepository.findById(movieEvaluateDTO.getId().getMovieId()).orElse(null);
                if (Objects.isNull(account)) {
                    throw new RuntimeException("Account does not exist");
                } else if (Objects.isNull(movie)) {
                    throw new RuntimeException("Movie does not exist");
                } else {
                    movieEvaluateRepository.saveEvaluate(movieEvaluateDTO.getId().getUserId(),
                            movieEvaluateDTO.getId().getMovieId(),
                            movieEvaluateDTO.getRate(),
                            movieEvaluateDTO.getContent(),
                            movieEvaluateDTO.getDate(),
                            movieEvaluateDTO.getStatus());
                    return true;
                }
            } else {
                movieEvaluateRepository.updateEvaluate(movieEvaluateDTO.getId().getUserId(),
                        movieEvaluateDTO.getId().getMovieId(),
                        movieEvaluateDTO.getRate(),
                        movieEvaluateDTO.getContent(),
                        movieEvaluateDTO.getDate(),
                        movieEvaluateDTO.getStatus());
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean checkBillingByAccId(Integer accId, Integer movieId) {
        Movie movie = movieDetailRepository.findById(movieId).orElse(null);
        List<BillingInformation> billingInformations = movie.getBillingInformations();
        if (Objects.isNull(billingInformations) || billingInformations.isEmpty()) {
            return false;
        } else {
            for (BillingInformation billingInformation : billingInformations) {
                if (billingInformation.getAccount().getId().equals(accId)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public PromotionDto getPromotion(String promotionCode) {
        PromotionDto promotionDto = promotionMapper.promotionToPromotionDto(promotionRepository.findPromotionByCode_name(promotionCode));
        return promotionDto;
    }

    @Override
    public List<String> getAllCountriesCode() {
        return movieDetailRepository.findAll().stream().map(movie -> {
            return movie.getCountryCode();
        }).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getMovieByGenreId(Integer genreId) {
        try {
            Genre genre = movieGenreRepository.findById(genreId).orElse(null);
            return genre.getGenreOfMovies().stream().map(genreOfMovie -> {
                        return movieMapper.movieToMovieDto(genreOfMovie.getMovie());
                    }).collect(Collectors.toList())

                    .stream().sorted(Comparator.comparing(MovieDto::getName).reversed())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MovieDto> getMovieByCountryCode(String code) {
        try {
            List<Movie> movies = movieDetailRepository.findAll();
            List<MovieDto> movieDtos = movies.parallelStream().filter(
                            movie -> movie.getCountryCode().equals(code))
                    .collect(Collectors.toList())
                    .stream().map(movie -> {
                        return movieMapper.movieToMovieDto(movie);
                    }).collect(Collectors.toList())
                    .stream().sorted(Comparator.comparing(MovieDto::getName).reversed())
                    .collect(Collectors.toList());
            return movieDtos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MovieDto> getMovieByGenreIdAndCountryCode(Integer genreId, String code) {
        List<MovieDto> movieDtos = getMovieByGenreId(genreId);
        return movieDtos.parallelStream().filter(
                        movieDto -> movieDto.getCountryCode().equals(code)).collect(Collectors.toList())
                .stream().sorted(Comparator.comparing(MovieDto::getName).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getPopularMovies() {
        List<PopularMovie> popularMovies = movieDetailRepository.findAll().stream()
                .map(movie -> {
                            try {
                                PopularMovie popularMovie = new PopularMovie();
                                popularMovie.setMovieDto(movieMapper.movieToMovieDto(movie));
                                popularMovie.setRate(getRateMovie(movie.getId()));
                                return popularMovie;
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).collect(Collectors.toList())
                .stream()
                .sorted(Comparator.comparingDouble(value -> value.getRate().getRate()))
                .collect(Collectors.toList());
        popularMovies.sort(Collections.reverseOrder((o1, o2) ->
                o1.getRate().getRate().compareTo(o2.getRate().getRate())));
        return popularMovies.stream().map(popularMovie -> {
            return popularMovie.getMovieDto();
        }).collect(Collectors.toList());
    }

    @Override
    public Integer getNumMovies() {
        return movieDetailRepository.getNumMovies();
    }

    @Override
    public Map<String, List<?>> getPieMovies() {
        Map<String, List<?>> obj = new HashMap<>();
        List<String> labels = new ArrayList<>();
        labels.add("Premiered");
        labels.add("Coming");
        obj.put("labels", labels);
        List<Integer> data = new ArrayList<>();
        data.add(movieDetailRepository.getPremiered());
        data.add(movieDetailRepository.getComing());
        obj.put("data", data);
        return obj;
    }

    public boolean checkExitNameEditMovie(String title, Integer id) {
        List<Movie> movies = movieDetailRepository.findAll();
        for (Movie movie : movies) {
            if (movie.getName().equals(title) && (id != movie.getId())) {
                return true;
            }
        }
        return false;
    }
}
