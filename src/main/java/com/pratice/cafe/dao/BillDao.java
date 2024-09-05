package com.pratice.cafe.dao;

import com.pratice.cafe.POJO.Bill;
import com.pratice.cafe.POJO.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillDao extends JpaRepository<Bill,Integer> {


}
