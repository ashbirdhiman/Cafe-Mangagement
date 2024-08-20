package com.pratice.cafe.service;

import com.pratice.cafe.POJO.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public interface CategoryService  {

    ResponseEntity<List<Category>> getAllCategory(String filterValue);


    public ResponseEntity<String> addNewCategory( Map<String,String> requestMapping);


    ResponseEntity<String> updateCategory(Map<String, String> requestMapping);
}
