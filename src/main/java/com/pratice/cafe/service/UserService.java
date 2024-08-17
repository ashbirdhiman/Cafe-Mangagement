package com.pratice.cafe.service;


import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestMapping);
    ResponseEntity<String> login(Map<String, String> requestMapping);
}
