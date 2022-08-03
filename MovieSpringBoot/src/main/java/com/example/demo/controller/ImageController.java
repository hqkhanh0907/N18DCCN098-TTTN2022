package com.example.demo.controller;

import com.example.demo.service.ImageService;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @RequestMapping(value = "/upload-image/{name}/{type}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JSONObject> fileUploadImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable("name") String name, @PathVariable("type") Integer type) {
        return new ResponseEntity<>(imageService.uploadImage(multipartFile, name, type), HttpStatus.OK);
    }

    @PostMapping("/getImage")
    public ResponseEntity<?> getImage(@RequestParam("url") String url) {
        return new ResponseEntity<>(imageService.getImage(url), HttpStatus.OK);
    }
}
