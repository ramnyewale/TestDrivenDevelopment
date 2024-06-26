package com.java.tdd.controller;

import com.java.tdd.model.Photo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PhotoController {

    @GetMapping("/photos")
    public List<Photo> getAllPhotos() {
        return List.of(new Photo(1, "Test Album", 11, "https://testthumbnail.png"),
                new Photo(2, "Test Album", 12, "https://testthumbnail.jpg"));
    }
}
