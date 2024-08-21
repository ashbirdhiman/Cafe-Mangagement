package com.pratice.cafe.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;

@org.hibernate.annotations.NamedQuery(name="Product.getAllProduct",query = "select new com.pratice.cafe.wrapper.ProductWrapper(p.id,p.name,p.category,p.description,p.price,p.status) from Product p")
@org.hibernate.annotations.NamedQuery(name="Product.findByCategoryID",query = "select new com.pratice.cafe.wrapper.ProductWrapper(p.id,p.name,p.category,p.description,p.price,p.status) from Product p where p.category=:id")



@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 132L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categeory_fk" ,nullable = true)
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private String status;

}
