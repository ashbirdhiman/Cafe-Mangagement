package com.pratice.cafe.rest;

import com.pratice.cafe.wrapper.UserWrapper;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@RequestMapping(path ="/user")
public interface UserRest {

    @PostMapping(path="/signup")
    public ResponseEntity<String> signup(@RequestBody(required=true) Map<String,String> requestMapping);

    @PostMapping(path="/login")
    public ResponseEntity<String> login(@RequestBody(required=true) Map<String,String> requestMapping);

    @GetMapping(path="/get")
    public ResponseEntity<List<UserWrapper>> getAllUser();

    @PostMapping(path="/update")
    public ResponseEntity<String> update(@RequestBody(required=true) Map<String,String> requestMapping);

    @PostMapping(path="/changepassword")
    public ResponseEntity<String> changePassword(@RequestBody(required=true) Map<String,String> requestMapping);


}
