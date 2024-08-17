package com.pratice.cafe.restImpl;

import com.pratice.cafe.constants.CafeConstants;
import com.pratice.cafe.rest.UserRest;
import com.pratice.cafe.service.UserService;
import com.pratice.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Service
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

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
}
