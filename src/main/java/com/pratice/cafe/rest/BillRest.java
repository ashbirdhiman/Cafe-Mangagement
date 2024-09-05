package com.pratice.cafe.rest;

import com.pratice.cafe.POJO.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/bill")
public interface BillRest {

    @PostMapping(path = "/generateReport")
    ResponseEntity<String> generateReport(@RequestBody Map<String,String> requestMapping);

    //TODO WATCH 8-9 videos on youtube
}
