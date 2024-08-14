package com.pratice.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public class CafeUtils {

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        String jsonResponse = "{\"message\":\"" + responseMessage + "\"}";
        return new ResponseEntity<>(jsonResponse, httpStatus);
    }
}
