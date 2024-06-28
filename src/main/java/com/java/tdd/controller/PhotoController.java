package com.java.tdd.controller;

import com.java.tdd.model.Photo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/population")
    public ResponseEntity<?> getPopulationCountByCityName(@RequestParam(required = false) String cityName) {
        if (cityName.equalsIgnoreCase("invalidCityName")) {
            return ResponseEntity.badRequest().body("Request failed due to invalid city name provided");
        }

        Long population = getpopulation(cityName);

        if (cityName.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(population);
    }

    private Long getpopulation(String cityName) {
        Long population;
        if (cityName == null || cityName.isEmpty()) { // Use isEmpty() to check for empty string
            return null;
        } else {
            if (cityName.equalsIgnoreCase("Pune"))
                population = 123456789L;
            else if (cityName.equalsIgnoreCase("Delhi"))
                population = 356982456L;
            else
                population = null;
        }
        return population;
    }
}
