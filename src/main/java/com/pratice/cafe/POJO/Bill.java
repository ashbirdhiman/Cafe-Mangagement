package com.pratice.cafe.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "bill")
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private Integer uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_number")
    private String contacNumber;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "totalAmount")
    private Integer totalAmount;

    @Column(name = "productDetails",columnDefinition = "json")
    private String productDetails;

    @Column(name = "created_by")
    private String createdBy;

}
