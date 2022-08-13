package com.example.demo.controller;

import com.example.demo.dto.CastDto;
import com.example.demo.dto.CastPage;
import com.example.demo.service.MovieCastService;
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
@RequestMapping("/api/cast")
public class CastController {
    private final MovieCastService movieCastService;

    //get page cast
    @GetMapping("/page")
    public CastPage getAllUsers(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo, @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize, @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return movieCastService.getAllCastPage(pageNo, pageSize, sortBy, sortDir);
    }

    //get all cast
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCast() {
        return new ResponseEntity<>(movieCastService.getAllCast(), HttpStatus.OK);
    }

    //get cast by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCastById(@PathVariable Integer id) {
        return new ResponseEntity<>(movieCastService.getMovieCastById(id), HttpStatus.OK);
    }

    //create a cast
    @PostMapping("/create")
    public ResponseEntity<?> createCast(@RequestBody CastDto castDto) {
        return new ResponseEntity<>(movieCastService.createMovieCast(castDto), HttpStatus.OK);
    }

    //edit cast
    @PutMapping("/edit")
    public ResponseEntity<?> editCast(@RequestBody CastDto castDto){
        return new ResponseEntity<>(movieCastService.editMovieCast(castDto), HttpStatus.OK);
    }

    //delete cats
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeCastById(@PathVariable Integer id){
        return new ResponseEntity<>(movieCastService.deleteMovieCastById(id),HttpStatus.OK);
    }
}
