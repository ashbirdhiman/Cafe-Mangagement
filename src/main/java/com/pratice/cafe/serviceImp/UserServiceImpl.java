package com.pratice.cafe.serviceImp;

import com.pratice.cafe.POJO.User;
import com.pratice.cafe.constants.CafeConstants;
import com.pratice.cafe.dao.UserDao;
import com.pratice.cafe.service.UserService;
import com.pratice.cafe.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMapping) {

        log.info("Inside Signup Method :{}",requestMapping);
        try{
            if(validRequestMap(requestMapping)){
                User user=userDao.findByEmailID(requestMapping.get("email"));
                if(Objects.isNull(user)){
                    userDao.save(getUserFromMap(requestMapping));
                    return CafeUtils.getResponseEntity("Sign Up Succesfull",HttpStatus.OK);
                }
                else{
                    return CafeUtils.getResponseEntity("Email Already Exist",HttpStatus.BAD_REQUEST);
                }
            }

        }
        catch (Exception e){
         e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST);
    }
    private boolean validRequestMap(Map<String,String> requestMapping){
        if(requestMapping.containsKey("name") && requestMapping.containsKey("email")
       && requestMapping.containsKey("contact_number") && requestMapping.containsKey("password")){
            return true;
        }
        return false;
    }

    public User getUserFromMap(Map<String,String> requestMap){
        User user =new User();
        user.setName(requestMap.get("name"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setContactNumber(requestMap.get("contact_number"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }

}
