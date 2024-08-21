package com.pratice.cafe.restImpl;

import com.pratice.cafe.POJO.Product;
import com.pratice.cafe.constants.CafeConstants;
import com.pratice.cafe.rest.ProductRest;
import com.pratice.cafe.service.ProductService;
import com.pratice.cafe.wrapper.ProductWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProductRestImpl implements ProductRest {

    @Autowired
    ProductService productService;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMapping) {
        log.info("inside get All Product function Product rest impl");
        try {
            return productService.addNewProduct(requestMapping);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        log.info("inside get All Product function Product rest impl");
        try {
            return productService.getAllProduct();

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMapping) {
        log.info("inside get All Product function Product rest impl");
        try {
            return productService.updateProduct(requestMapping);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        log.info("inside get All Product function Product rest impl");
        try {
            return productService.deleteProduct(id);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
        log.info("inside get category by ID function product rest impl");
        try {
            return productService.getByCategory(id);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> getByID(Integer id) {
        log.info("inside get Product by ID function product rest impl");
        try {
            return productService.getByID(id);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }
    }

}
