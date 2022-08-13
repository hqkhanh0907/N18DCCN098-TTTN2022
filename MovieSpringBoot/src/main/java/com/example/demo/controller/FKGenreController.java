package com.example.demo.controller;

import com.example.demo.service.FKGenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/fkGenre")
public class FKGenreController {
    private final FKGenreService fkGenreService;
    @GetMapping("/getAllFKGenre")
    public ResponseEntity<?> getAllFKGenre(){
        return new ResponseEntity<>(fkGenreService.getAllFKGenre(), HttpStatus.OK);
    }

    //get all genre on a movie
    @GetMapping("/getAllGenre/{movieId}")
    public ResponseEntity<?> getAllGenreByMovie(@PathVariable Integer movieId){
        return new ResponseEntity<>(fkGenreService.getGenreOnMovie(movieId), HttpStatus.OK);
    }

    //get all movie on genre
    @GetMapping("/getAllMovie/{genreId}")
    public ResponseEntity<?> getAllMovieByGenre(@PathVariable Integer genreId){
        return new ResponseEntity<>(fkGenreService.getMovieOnGenre(genreId), HttpStatus.OK);
    }


}
