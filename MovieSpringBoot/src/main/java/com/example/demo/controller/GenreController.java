package com.example.demo.controller;

import com.example.demo.dto.GenreDto;
import com.example.demo.service.MovieGenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/genre")
public class GenreController {
    private final MovieGenreService movieGenreService;


    //don't need page genre
    //get all genre
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllGenres() {
        return new ResponseEntity<>(movieGenreService.getAllMovieGen(), HttpStatus.OK);
    }

    //get genre by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getGenreById(@PathVariable Integer id) {
        return new ResponseEntity<>(movieGenreService.getMovieGenreById(id), HttpStatus.OK);
    }

    //Create a genre
    @PostMapping("/create")
    public ResponseEntity<?> createGenre(@RequestBody GenreDto movieGenreDTO) {
        return new ResponseEntity<>(movieGenreService.createMovieGenre(movieGenreDTO), HttpStatus.OK);
    }

    //Edit genre
    @PutMapping("/edit")
    public ResponseEntity<?> editGenre(@RequestBody GenreDto genreDto) {
        return new ResponseEntity<>(movieGenreService.editMovieGenre(genreDto), HttpStatus.OK);
    }

    //Delete genre by id
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteGenreById(@PathVariable Integer id) {
        return new ResponseEntity<>(movieGenreService.deleteMovieGenreById(id), HttpStatus.OK);
    }

    @GetMapping("/getNumGenre")
    public ResponseEntity<?> getNumGenre() {
        return new ResponseEntity<>(movieGenreService.getNumGenre(), HttpStatus.OK);
    }
    
    @GetMapping("/getGenreBar")
    public ResponseEntity<?> getGenreBar() {
        return new ResponseEntity<>(movieGenreService.getGenreBar(), HttpStatus.OK);
    }
}
