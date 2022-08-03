package com.example.demo.controller;

import com.example.demo.dto.DirectorDto;
import com.example.demo.dto.DirectorPage;
import com.example.demo.service.MovieDirectorService;
import com.example.demo.util.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/director")
public class DirectorController {
    private final MovieDirectorService movieDirectorService;

    //get all director
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllDirectors() {
        return new ResponseEntity<>(movieDirectorService.getAllMovieDirector(), HttpStatus.OK);
    }
    //get page directors

    @GetMapping("/page")
    public DirectorPage getAllUsers(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo, @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize, @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return movieDirectorService.getAllDirectorPage(pageNo, pageSize, sortBy, sortDir);
    }

    //create a director
    @PostMapping("/create")
    public ResponseEntity<?> createDirector(@RequestBody DirectorDto directorDto) {
        return new ResponseEntity<>(movieDirectorService.createMovieDirector(directorDto), HttpStatus.OK);
    }

    //Edit a director
    @PutMapping("/edit")
    public ResponseEntity<?> editDirector(@RequestBody DirectorDto directorDto) {
        return new ResponseEntity<>(movieDirectorService.editMovieDirector(directorDto), HttpStatus.OK);
    }

    //Delete a director
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable int id) {
        return new ResponseEntity<>(movieDirectorService.deleteMovieDirectorById(id), HttpStatus.OK);
    }
}
