package com.pratice.cafe.service;

import com.pratice.cafe.POJO.Product;
import com.pratice.cafe.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public interface ProductService {

    ResponseEntity<List<ProductWrapper>> getAllProduct();


    public ResponseEntity<String> addNewProduct( Map<String,String> requestMapping);


    ResponseEntity<String> updateProduct(Map<String, String> requestMapping);

    ResponseEntity<String> deleteProduct(Integer id);

    ResponseEntity<List<ProductWrapper>> getByCategory(Integer id);

    ResponseEntity<String> getByID(Integer id);
}
