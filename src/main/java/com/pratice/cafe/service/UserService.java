package com.pratice.cafe.service;


import com.pratice.cafe.wrapper.UserWrapper;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestMapping);

    ResponseEntity<String> login(Map<String, String> requestMapping);

    ResponseEntity<List<UserWrapper>> getAllUser();

    ResponseEntity<String> update(Map<String, String> requestMapping);

    ResponseEntity<String> changePassword(Map<String, String> requestMapping);
}
