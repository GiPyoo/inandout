package com.mappractice.demo.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class ResponseGenerator {

    public static <T> ResponseEntity<RestResponse<T>> generateListResponseEntity(T body, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(RestResponse.success(body), headers, httpStatus);
    }

    public static <T> ResponseEntity<RestResponse<T>> generateResponseEntity(T body, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(RestResponse.success(body), headers, httpStatus);
    }

    public static <T> ResponseEntity<RestResponse<T>> generateResponseEntity(T body, String message, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(RestResponse.success(body, message), headers, httpStatus);
    }

    public static <T> ResponseEntity<RestResponse<T>> generateCreatedResponseEntity(T body, String path) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(path));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(RestResponse.success(body), headers, HttpStatus.CREATED);
    }
}
