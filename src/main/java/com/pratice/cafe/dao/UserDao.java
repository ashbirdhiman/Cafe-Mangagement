package com.pratice.cafe.dao;

import com.pratice.cafe.POJO.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmailID(@Param("email") String email);
}
