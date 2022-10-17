package com.example.demo.controller;

import com.example.demo.dto.BillingInformationDto;
import com.example.demo.dto.MovieEvaluateDto;
import com.example.demo.model.Movie;
import com.example.demo.service.BillingInformationService;
import com.example.demo.service.MovieDetailService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movieDetail")
public class MovieDetailController {
    private final MovieDetailService movieDetailService;
    private final BillingInformationService billingInformationService;

//    @GetMapping("/page")
//    public MovieDetailPage getAllUsers(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo, @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize, @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
//        return movieDetailService.getAllMovieDetailPage(pageNo, pageSize, sortBy, sortDir);
//    }

    @GetMapping("/getMovieDetail/{id}")
    public ResponseEntity<?> getMovieDetailById(@PathVariable Integer id) {
        return new ResponseEntity<>(movieDetailService.getMovieById(id), HttpStatus.OK);
    }
    @GetMapping("/checkBillByAccId/{accId}/{movieId}")
    public ResponseEntity<?> checkBillingByAccId(@PathVariable("accId") Integer accId,@PathVariable("movieId") Integer movieId) {
        return new ResponseEntity<>(movieDetailService.checkBillingByAccId(accId, movieId), HttpStatus.OK);
    }

    @GetMapping("/getMovieDetailAll")
    public ResponseEntity<?> getMovieDetailAll() {
        return new ResponseEntity<>(movieDetailService.getAllMovie(), HttpStatus.OK);
    }

    @PostMapping("/addInfoBill")
    public ResponseEntity<?> addInfoBill(@RequestBody BillingInformationDto billingInformation) {
        return new ResponseEntity<>(billingInformationService.addInfoBill(billingInformation), HttpStatus.OK);
    }

    @GetMapping("/getPromo/{promo_code}")
    public ResponseEntity<?> getPromo(@PathVariable("promo_code") String promotionCode) {
        return new ResponseEntity<>(movieDetailService.getPromotion(promotionCode), HttpStatus.OK);
    }

    @GetMapping("/getAllCountriesCode")
    public ResponseEntity<?> getAllCountriesCode() {
        return new ResponseEntity<>(movieDetailService.getAllCountriesCode(), HttpStatus.OK);
    }

    @GetMapping("/getMovieRates")
    public ResponseEntity<?> getMovierates() throws Exception {
        return new ResponseEntity<>(movieDetailService.getListMovieRate(), HttpStatus.OK);
    }

    @GetMapping("/getMovieRate/{id}")
    public ResponseEntity<?> getMovieRate(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(movieDetailService.getRateMovie(id), HttpStatus.OK);
    }

    @GetMapping("/getGenreByMovieId/{id}")
    public ResponseEntity<?> getGenreByMovieId(@PathVariable Integer id) {
        return new ResponseEntity<>(movieDetailService.getMovieGenres(id), HttpStatus.OK);
    }

    @GetMapping("/getMovieByGenreId/{id}")
    public ResponseEntity<?> getMovieByGenreId(@PathVariable("id") Integer genreId) {
        return new ResponseEntity<>(movieDetailService.getMovieByGenreId(genreId), HttpStatus.OK);
    }

    @GetMapping("/getMovieByCountryCode/{code}")
    public ResponseEntity<?> getMovieByCountryCode(@PathVariable("code") String code) {
        return new ResponseEntity<>(movieDetailService.getMovieByCountryCode(code), HttpStatus.OK);
    }

    @GetMapping("/getMovieByGenreIdAndCountryCode/{genreId}/{code}")
    public ResponseEntity<?> getMovieByGenreIdAndCountryCode(@PathVariable("code") String code, @PathVariable Integer genreId) {
        return new ResponseEntity<>(movieDetailService.getMovieByGenreIdAndCountryCode(genreId, code), HttpStatus.OK);
    }

    @GetMapping("/getPopularMovies")
    public ResponseEntity<?> getPopularMovies() {
        return new ResponseEntity<>(movieDetailService.getPopularMovies(), HttpStatus.OK);
    }


    @GetMapping("/getCastByMovieId/{id}")
    public ResponseEntity<?> getCastByMovieId(@PathVariable Integer id) {
        return new ResponseEntity<>(movieDetailService.getMovieCasts(id), HttpStatus.OK);
    }

    @GetMapping("/getDirectorByMovieId/{id}")
    public ResponseEntity<?> getDirectorByMovieId(@PathVariable Integer id) {
        return new ResponseEntity<>(movieDetailService.getMovieDirectors(id), HttpStatus.OK);
    }

    @GetMapping("/getMovieDetailByName")
    public ResponseEntity<?> getMovieDetailByName(@Param("name") String name) throws Exception {
        return new ResponseEntity<>(movieDetailService.getMovieDetailByName(name), HttpStatus.OK);
    }

    @PostMapping("/getMovieDetailBySlug")
    public ResponseEntity<?> getMovieDetailBySlug(@Param("slug") String slug) throws Exception {
        return new ResponseEntity<>(movieDetailService.getMovieBySlug(slug), HttpStatus.OK);
    }

    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovieDetail(@RequestBody Movie movieDTO) throws Exception {
        return new ResponseEntity<>(movieDetailService.addMovieDetail(movieDTO), HttpStatus.OK);
    }

    @PutMapping("/editMovieDetail")
    public ResponseEntity<?> editMovieDetail(@RequestBody Movie movieDTO) throws Exception {
        return new ResponseEntity<>(movieDetailService.editMovieDetail(movieDTO), HttpStatus.OK);
    }

    @PostMapping("/saveEvaluate")
    public ResponseEntity<?> saveEvaluate(@RequestBody MovieEvaluateDto movieEvaluateDTO) throws Exception {
        return new ResponseEntity<>(movieDetailService.saveEvaluate(movieEvaluateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeMovie(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(movieDetailService.deleteMovieDetail(id), HttpStatus.OK);
    }

    @GetMapping("/search/{searchKey}")
    public ResponseEntity<?> fullTextSearch(@PathVariable String searchKey) {
        String searchQuery = "%" + searchKey + "%";
        return new ResponseEntity<>(movieDetailService.search(searchQuery), HttpStatus.OK);

    }

    @GetMapping("/loadEvaluateInMovie/{idMovie}")
    public ResponseEntity<?> loadEvaluateInMovie(@PathVariable("idMovie") Integer movieId) {
        return new ResponseEntity<>(movieDetailService.loadEvaluate(movieId), HttpStatus.OK);
    }

    @GetMapping("/loadEvaluateInAccount/{idAcc}")
    public ResponseEntity<?> loadEvaluateInAccount(@PathVariable("idAcc") Integer accId) {
        return new ResponseEntity<>(movieDetailService.loadEvaluateInAcc(accId), HttpStatus.OK);
    }

}
