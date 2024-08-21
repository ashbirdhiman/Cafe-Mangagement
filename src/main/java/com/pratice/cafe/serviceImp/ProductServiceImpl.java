package com.pratice.cafe.serviceImp;

import com.pratice.cafe.JWT.JwtFilter;
import com.pratice.cafe.POJO.Category;
import com.pratice.cafe.POJO.Product;
import com.pratice.cafe.constants.CafeConstants;
import com.pratice.cafe.dao.ProductDao;

import com.pratice.cafe.service.ProductService;
import com.pratice.cafe.utils.CafeUtils;
import com.pratice.cafe.wrapper.ProductWrapper;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    JwtFilter filter;

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        try{
               return new ResponseEntity<List<ProductWrapper>>(productDao.getAllProduct(),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMapping) {
        log.info("Inside Add Product Method :{}",requestMapping);
        try{
            if(validRequestMap(requestMapping,false) && filter.isAdmin() ){
                    productDao.save(getProductFromMap(requestMapping,false));
                    return CafeUtils.getResponseEntity("New Product Added Successfully", HttpStatus.OK);
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
    public ResponseEntity<String> updateProduct(Map<String, String> requestMapping) {
        log.info("Inside Update Product Method :{}",requestMapping);
        try{
            if(validRequestMap(requestMapping,true) && filter.isAdmin() ){
                Optional<Product> Product=productDao.findById(Integer.valueOf(requestMapping.get("id")));
                    if(Product.isPresent()){
                        productDao.save(getProductFromMap(requestMapping,true));
                        return CafeUtils.getResponseEntity(" Product Updated Successfully", HttpStatus.OK);
                    }
                    else {
                                return CafeUtils.getResponseEntity(" Product Id doesn't exist", HttpStatus.OK);
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

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        log.info("Inside Update Product Method Id:{}",id);
        try{
                Optional<Product> product=productDao.findById(id);
                if(product.isPresent()){
                    productDao.delete(product.get());
                    return CafeUtils.getResponseEntity(" Product Deleted Successfully", HttpStatus.OK);

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
    public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
        log.info("Inside get Product Method ID:{}",id);
        try {
            List<ProductWrapper> products = productDao.findByCategoryID(id);
            if(!products.isEmpty()){
                return new ResponseEntity<List<ProductWrapper>>(products,HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> getByID(Integer id) {
        log.info("Inside get Product Method ID:{}",id);
        try {
            Optional<Product> product = productDao.findById(id);
            return product.map(value -> CafeUtils.getResponseEntity(value.toString(), HttpStatus.OK))
                    .orElseGet(() -> CafeUtils.getResponseEntity("Category ID doesn't exist", HttpStatus.OK));

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
            //In case of update Product
            else return !validateId;
        }
        return false;
    }


    public Product getProductFromMap(Map<String,String> requestMap,boolean isAdd){
        Category category=new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));

        Product product =new Product();
        if(isAdd){
            product.setId(Integer.parseInt(requestMap.get("id")));

        }else{
            product.setStatus("true");
        }
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        product.setCategory(category);

        return product;
    }
}
