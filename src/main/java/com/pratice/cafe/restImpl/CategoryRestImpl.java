package com.pratice.cafe.restImpl;

import com.pratice.cafe.POJO.Category;
import com.pratice.cafe.constants.CafeConstants;
import com.pratice.cafe.rest.CategoryRest;
import com.pratice.cafe.service.CategoryService;
import com.pratice.cafe.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CategoryRestImpl implements CategoryRest {

    @Autowired
    CategoryService categoryService;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMapping) {
        log.info("inside get All category function category rest impl");
        try {
            return categoryService.addNewCategory(requestMapping);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        log.info("inside get All category function category rest impl");
        try {
            return categoryService.getAllCategory(filterValue);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMapping) {
        log.info("inside update category function category rest impl");
        try {
            return categoryService.updateCategory(requestMapping);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }
    }



}
