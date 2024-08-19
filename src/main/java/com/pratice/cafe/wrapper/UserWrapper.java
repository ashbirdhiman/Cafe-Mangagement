package com.pratice.cafe.wrapper;


import com.pratice.cafe.POJO.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {

    private int id;

    private String name;

    private String email;

    private String password;

    private String contact_number;

    private String status;

    public UserWrapper(int id, String name, String email, String password, String contact_number, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact_number = contact_number;
        this.status = status;
    }

}
