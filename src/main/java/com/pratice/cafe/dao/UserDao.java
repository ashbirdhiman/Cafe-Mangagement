package com.pratice.cafe.dao;

import com.pratice.cafe.POJO.User;

import com.pratice.cafe.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmailID(@Param("email") String email);

    List<UserWrapper> getAllUser();

    List<UserWrapper> getAllAdmins();

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status")String status,@Param("id") int id);



}
