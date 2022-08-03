package com.example.demo.controller;

import com.example.demo.service.CastOfMovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/fkCast")
public class FKCastController {
    private final CastOfMovieService castOfMovieService;

    @GetMapping("/getAllFkCast")
    public ResponseEntity<?> getAllFkCast(){
        return new ResponseEntity<>(castOfMovieService.getAllFKCast(), HttpStatus.OK);
    }

    // get all movie on a cast
    @GetMapping("/movie/{castId}")
    public ResponseEntity<?> getAllMovieOnACast(@PathVariable int castId) {
        return new ResponseEntity<>(castOfMovieService.getMovieDetailByCastId(castId), HttpStatus.OK);
    }

    // get all movie on a cast
    @GetMapping("/cast/{movieId}")
    public ResponseEntity<?> getAllCastOnAMovie(@PathVariable int movieId) {
        return new ResponseEntity<>(castOfMovieService.getCastByMovieId(movieId), HttpStatus.OK);
    }

}
