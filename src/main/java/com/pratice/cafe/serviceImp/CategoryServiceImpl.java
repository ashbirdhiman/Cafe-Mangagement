package com.pratice.cafe.serviceImp;

import com.pratice.cafe.JWT.JwtFilter;
import com.pratice.cafe.POJO.Category;
import com.pratice.cafe.constants.CafeConstants;
import com.pratice.cafe.dao.CategoryDao;
import com.pratice.cafe.service.CategoryService;
import com.pratice.cafe.utils.CafeUtils;
import com.pratice.cafe.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    JwtFilter filter;

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try{
           if(filterValue!=null && filterValue.equalsIgnoreCase("true")){
               return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(),HttpStatus.OK);
           }
           return new ResponseEntity<List<Category>>(categoryDao.findAll(),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMapping) {
        log.info("Inside Add Category Method :{}",requestMapping);
        try{
            if(validRequestMap(requestMapping,false) && filter.isAdmin() ){
                    categoryDao.save(getCategoryFromMap(requestMapping,false));
                    return CafeUtils.getResponseEntity("New Category Added Successfully", HttpStatus.OK);
                }
            else {
                return CafeUtils.getResponseEntity("Unauthorized Access", HttpStatus.UNAUTHORIZED);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMapping) {
        log.info("Inside Update Category Method :{}",requestMapping);
        try{
            if(validRequestMap(requestMapping,true) && filter.isAdmin() ){
                Optional<Category> category=categoryDao.findById(Integer.valueOf(requestMapping.get("id")));
                    if(category.isPresent()){
                        categoryDao.save(getCategoryFromMap(requestMapping,true));
                        return CafeUtils.getResponseEntity(" Category Updated Successfully", HttpStatus.OK);
                    }
                    else {
                                return CafeUtils.getResponseEntity(" Category Id doesn't exist", HttpStatus.OK);
                            }
            }
            else {
                return CafeUtils.getResponseEntity("Unauthorized Access", HttpStatus.UNAUTHORIZED);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST);
    }

    private boolean validRequestMap(Map<String, String> requestMapping,boolean validateId) {
        if(requestMapping.containsKey("name")){
            if(requestMapping.containsKey("id") && validateId){
                return true;
            }
            //In case of update category
            else return !validateId;
        }
        return false;
    }


    public Category getCategoryFromMap(Map<String,String> requestMap,boolean isAdd){
        Category category =new Category();
        if(isAdd){
            category.setId(Integer.parseInt(requestMap.get("id")));
        }
        category.setName(requestMap.get("name"));

        return category;
    }
}
