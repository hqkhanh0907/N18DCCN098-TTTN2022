package com.example.demo.controller;

import com.example.demo.model.CastOfMovie;
import com.example.demo.model.GenreOfMovie;
import com.example.demo.repository.FKCastRepository;
import com.example.demo.repository.FKGenreRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.CastOfMovieService;
import com.example.demo.service.FKGenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class testController {
    private final AccountService accountService;
    private final FKCastRepository fkCastRepository;
    private final CastOfMovieService castOfMovieService;
    private final FKGenreService fkGenreService;
    private final FKGenreRepository fkGenreRepository;

    @GetMapping("/deleteCastByMovie/{id}")
    public ResponseEntity<?> getAllAcc(@PathVariable("id") Integer id) {
        castOfMovieService.deleteFkCastByMovieId(id);
        List<CastOfMovie> castOfMovies = fkCastRepository.findAll();
        System.out.println(castOfMovies);
        return new ResponseEntity<>(castOfMovies, HttpStatus.OK);
    }

    @GetMapping("/deleteGenreByMovie/{id}")
    public ResponseEntity<?> test(@PathVariable("id") Integer id) {
        fkGenreService.removeGenreExits(id);
        List<GenreOfMovie> castOfMovies = fkGenreRepository.findAll();
        System.out.println(castOfMovies);
        return new ResponseEntity<>(castOfMovies, HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity<?> responseEntity() {
        return new ResponseEntity<>(accountService.getAccountById(1), HttpStatus.OK);
    }
}
