package com.example.demo.controller;

import com.example.demo.dto.PromotionDto;
import com.example.demo.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/promotion")
public class PromotionController {
    private final PromotionService promotionService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPromotion() {
        return new ResponseEntity<>(promotionService.getAllPromotion(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPromotion(@RequestBody PromotionDto promotionDto) {
        return new ResponseEntity<>(promotionService.addPromotion(promotionDto), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editPromotion(@RequestBody PromotionDto promotionDto) {
        return new ResponseEntity<>(promotionService.editPromotion(promotionDto), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removePromotion(@PathVariable("id") Integer id){
        return new ResponseEntity<>(promotionService.removePromotion(id), HttpStatus.OK);
    }
}
