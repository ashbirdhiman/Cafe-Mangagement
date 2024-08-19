package com.pratice.cafe.restImpl;

import com.pratice.cafe.JWT.JwtFilter;
import com.pratice.cafe.POJO.User;
import com.pratice.cafe.constants.CafeConstants;
import com.pratice.cafe.rest.UserRest;
import com.pratice.cafe.service.UserService;
import com.pratice.cafe.utils.CafeUtils;
import com.pratice.cafe.wrapper.UserWrapper;
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
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public  ResponseEntity<String> signup(Map<String, String> requestMapping) {
        try{
            return userService.signUp(requestMapping);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMapping) {
        try {
            return userService.login(requestMapping);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
            log.info("inside get All user function user rest impl");
        try {
               return userService.getAllUser();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMapping) {
        try {
            return userService.changePassword(requestMapping);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    public ResponseEntity<String> update(Map<String, String> requestMapping) {
        try {
            return userService.update(requestMapping);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
