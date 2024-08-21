package com.pratice.cafe.dao;

import com.pratice.cafe.POJO.Product;
import com.pratice.cafe.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {

    List<ProductWrapper> getAllProduct();

    List<ProductWrapper> findByCategoryID(Integer id);
}
