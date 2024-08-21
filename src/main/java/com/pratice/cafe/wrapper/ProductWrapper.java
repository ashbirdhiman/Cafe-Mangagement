package com.pratice.cafe.wrapper;

import com.pratice.cafe.POJO.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class ProductWrapper {
    private Integer id;

    private String name;

    private Category category;

    private String description;

    private Integer price;

    private String status;

    public ProductWrapper(Integer id, String name, Category category, String description, Integer price, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.status = status;
    }
}
