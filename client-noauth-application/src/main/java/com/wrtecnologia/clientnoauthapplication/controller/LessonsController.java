package com.wrtecnologia.clientnoauthapplication.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LessonsController {

    // @Value("${api.lessons-url}")
    // private String lessonsUrl;

    private final RestClient restClient;

    // @Value("${api.lessons-url}")
    // private String lessonsUrl;

    public LessonsController(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://resource-server:8082")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
                    HttpStatusCode statusCode = response.getStatusCode();
                    throw new ResponseStatusException(statusCode); // Lança a exceção genérica
                })
                .build();
    }

    @GetMapping("/lessons")
    public String fetchLessons() {
        return restClient.get()
            .uri("/lessons")
            .retrieve()
            .body(String.class);
    }
}