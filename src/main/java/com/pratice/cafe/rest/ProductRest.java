package com.pratice.cafe.rest;

import com.pratice.cafe.POJO.Product;
import com.pratice.cafe.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/product")
public interface ProductRest {


    @PostMapping(path="/add")
    public ResponseEntity<String> addNewProduct(@RequestBody(required=true) Map<String,String> requestMapping);

    @GetMapping("/getAll")
    ResponseEntity<List<ProductWrapper>> getAllProduct();

    @PostMapping(path="/update")
    public ResponseEntity<String> updateProduct(@RequestBody(required=true) Map<String,String> requestMapping);

    @GetMapping(path="/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id);

    @PostMapping(path="/getByCategory/{id}")
    public ResponseEntity<List<ProductWrapper>> getByCategory(@PathVariable Integer id);

    @PostMapping(path="c")
    public ResponseEntity<String> getByID(@PathVariable Integer id);
}
