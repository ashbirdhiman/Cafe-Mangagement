package com.pratice.cafe.rest;

import com.pratice.cafe.POJO.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/category")
public interface CategoryRest {

    @PostMapping(path="/add")
    public ResponseEntity<String> addNewCategory(@RequestBody(required=true) Map<String,String> requestMapping);

    @GetMapping("/getAll")
    ResponseEntity<List<Category>> getAllCategory(@RequestParam(required=false) String filterValue);

    @PostMapping(path="/updateCategory")
    public ResponseEntity<String> updateCategory(@RequestBody(required=true) Map<String,String> requestMapping);


}
