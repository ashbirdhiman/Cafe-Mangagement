package com.pratice.cafe.restImpl;

import com.pratice.cafe.POJO.Category;
import com.pratice.cafe.constants.CafeConstants;
import com.pratice.cafe.rest.BillRest;
import com.pratice.cafe.rest.CategoryRest;
import com.pratice.cafe.service.BillService;
import com.pratice.cafe.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BillRestImpl implements BillRest {

    @Autowired
    BillService billService;

    @Override
    public ResponseEntity<String> generateReport(Map<String, String> requestMapping) {
        log.info("inside generate Repost Bill rest impl");
        try {
            return billService.generateReport(requestMapping);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }
    }
}
