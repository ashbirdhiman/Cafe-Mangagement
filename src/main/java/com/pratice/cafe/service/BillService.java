package com.pratice.cafe.service;

import com.pratice.cafe.POJO.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public interface BillService {


    ResponseEntity<String> generateReport(Map<String, String> requestMapping);
}
