package com.example.demo.service.implement;

import com.example.demo.dto.*;
import com.example.demo.dto.map.*;
import com.example.demo.model.*;
import com.example.demo.model.Key.BillingInformationKey;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
    public List<MovieDto> search(String searchQuery) {
        return movieDetailRepository.findByNameLike(searchQuery).stream().map(movie -> {
            return movieMapper.movieToMovieDto(movie);
        }).collect(Collectors.toList());
    }

    @Override
    public List<MovieEvaluateDto> loadEvaluate(Integer movieId) {
        return movieEvaluateService.getMovieEvaluates().stream().map(movieEvaluate -> {
            if (movieEvaluate.getId().getMovieId() == movieId) {
                return movieEvaluateMapper.movieEvaluateToMovieEvaluateDto(movieEvaluate);
            } else {
                return null;
            }
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
    public Movie saveEvaluate(MovieEvaluateDto movieEvaluateDTO) {
        Movie movie = movieDetailRepository.findById(movieEvaluateDTO.getId().getMovieId()).orElse(null);
        try {
            MovieEvaluate movieEvaluate = movieEvaluateMapper.movieEvaluateDtoToMovieEvaluate(movieEvaluateDTO);
            movieEvaluate.setMovie(movie);
            movieEvaluate.setAccount(accountRepository.findById(movieEvaluateDTO.getId().getUserId()).orElse(null));
            List<MovieEvaluate> movieEvaluates = new ArrayList<>();
            if (movie.getMovieEvaluates() != null) {
                movieEvaluates = movie.getMovieEvaluates();
                for (MovieEvaluate movieEvaluateCheck : movieEvaluates) {
                    if ((movieEvaluateCheck.getId().getUserId() == movieEvaluate.getId().getUserId()) &&
                            (movieEvaluateCheck.getId().getMovieId() == movieEvaluate.getId().getMovieId())) {
                        movieEvaluateService.editEvaluate(movieEvaluate);
                        return movie;
                    }
                }
                movieEvaluates.add(movieEvaluate);
            } else {
                movieEvaluates.add(movieEvaluate);
            }
            movie.setMovieEvaluates(movieEvaluates);
            movieDetailRepository.save(movie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return movie;
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
    public Boolean addInfoBill(BillingInformationDto billingInformationDto) {
        Movie movie = movieDetailRepository.findById(billingInformationDto.getBillingInformationKey().getMovieId()).orElse(null);
        Promotion promotion = promotionRepository.findById(billingInformationDto.getPromotion().getId()).orElse(null);
        List<BillingInformation> billingInformations = movie.getBillingInformations();
        BillingInformation billingInformation = new BillingInformation();
        Integer accountId = billingInformationDto.getBillingInformationKey().getAccountId();
        Integer movieId = billingInformationDto.getBillingInformationKey().getMovieId();
        BillingInformationKey billingInformationKey = new BillingInformationKey(accountId, movieId);
        billingInformation.setBillingInformationKey(billingInformationKey);
        billingInformation.setAccount(accountRepository.findById(billingInformationDto.getBillingInformationKey().getAccountId()).orElse(null));
        billingInformation.setMovie(movie);
        billingInformation.setPromotion(promotion);
        billingInformation.setStatus(billingInformationDto.getStatus());
        billingInformations.add(billingInformation);
        movie.setBillingInformations(billingInformations);
        movieDetailRepository.save(movie);
        return true;
    }

    @Override
    public PromotionDto getPromotion(String promotionCode) {
        PromotionDto promotionDto = promotionMapper.promotionToPromotionDto(promotionRepository.findPromotionByCode_name(promotionCode));
        return promotionDto;
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
