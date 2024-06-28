package com.java.tdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.tdd.controller.PhotoController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhotoController.class)
@AutoConfigureMockMvc
public class PhotoControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldFetchAllPhotos() throws Exception {
        mockMvc.perform(get("/v1/photos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    void shouldReturnPopulationByCityName() throws Exception {
        Long expected = 123456789L;
        String response = mockMvc.perform(get("/v1/population")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("cityName", "Pune"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        Long population = objectMapper.readValue(response, Long.class);
        assertEquals(expected, population);
    }

    @Test
    void shouldReturnPopulationByCityName_ThrowsException_IfInvalidCityNameProvided() throws Exception {
        String cityName = "";
        this.mockMvc.perform(get("/v1/population")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("cityName", cityName))
                .andExpect(status().isNoContent());

        String response = this.mockMvc.perform(get("/v1/population")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("cityName", "invalidCityName"))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(response, "Request failed due to invalid city name provided");
    }
}
