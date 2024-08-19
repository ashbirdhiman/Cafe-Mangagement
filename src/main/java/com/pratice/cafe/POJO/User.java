package com.pratice.cafe.POJO;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;


@org.hibernate.annotations.NamedQuery(name="User.findByEmailID",query = "select u from User u where u.email=:email")
@org.hibernate.annotations.NamedQuery(name="User.getAllUser",query = "select new com.pratice.cafe.wrapper.UserWrapper(u.id,u.name,u.email,u.password,u.contactNumber,u.status) from User u where u.role='user'")
@org.hibernate.annotations.NamedQuery(name="User.updateStatus",query = "Update User u set status=:status where id=:id")
@org.hibernate.annotations.NamedQuery(name="User.getAllAdmins",query = "select u.email from User u where u.role='admin'")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Column(name = "role")
    private String role;


}
