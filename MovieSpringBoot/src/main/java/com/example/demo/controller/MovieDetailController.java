package com.example.demo.controller;

import com.example.demo.dto.BillingInformationDto;
import com.example.demo.dto.MovieEvaluateDto;
import com.example.demo.model.Movie;
import com.example.demo.service.MovieDetailService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movieDetail")
public class MovieDetailController {
    private final MovieDetailService movieDetailService;
    private final EntityManager entityManager;

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
        return new ResponseEntity<>(movieDetailService.addInfoBill(billingInformation), HttpStatus.OK);
    }
    @GetMapping("/getPromo/{promo_code}")
    public ResponseEntity<?> getPromo(@PathVariable("promo_code") String promotionCode) {
        return new ResponseEntity<>(movieDetailService.getPromotion(promotionCode),HttpStatus.OK);
    }
    @GetMapping("/getMovieRates")
    public ResponseEntity<?> getMovierates() throws Exception {
        return new ResponseEntity<>(movieDetailService.getListMovieRate(), HttpStatus.OK);
    }

    @GetMapping("/getMovieRate/{id}")
    public ResponseEntity<?> getMovierate(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(movieDetailService.getRateMovie(id), HttpStatus.OK);
    }

    @GetMapping("/getGenreByMovieId/{id}")
    public ResponseEntity<?> getGenreByMovieId(@PathVariable Integer id) {
        return new ResponseEntity<>(movieDetailService.getMovieGenres(id), HttpStatus.OK);
    }

    @GetMapping("/getCastByMovieId/{id}")
    public ResponseEntity<?> getCastByMovieId(@PathVariable Integer id) {
        return new ResponseEntity<>(movieDetailService.getMovieCasts(id), HttpStatus.OK);
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

    @PutMapping("/saveEvaluate")
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
