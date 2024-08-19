package com.pratice.cafe.serviceImp;

import com.pratice.cafe.JWT.CustomerUserDetailsService;
import com.pratice.cafe.JWT.JwtFilter;
import com.pratice.cafe.JWT.JwtUtils;
import com.pratice.cafe.POJO.User;
import com.pratice.cafe.constants.CafeConstants;
import com.pratice.cafe.dao.UserDao;
import com.pratice.cafe.service.UserService;
import com.pratice.cafe.utils.CafeUtils;
import com.pratice.cafe.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtFilter filter;

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

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMapping) {
        log.info("Inside login Method :{}", requestMapping);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMapping.get("email"), requestMapping.get("password")));

            if (authentication.isAuthenticated()) {
                if (customerUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<String>("{\"token\":\"" +
                            jwtUtils.generateToken(customerUserDetailsService.getUserDetails().getEmail(), customerUserDetailsService.getUserDetails().getRole()) + "\"}",
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\"Wait for admin approval\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<String>("{\"message\":\"Invalid credentials\"}", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during authentication or token generation
            e.printStackTrace(); // Log the exception for debugging purposes (optional)
            return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {

        try {
            if (filter.isAdmin()) {
                return new ResponseEntity<List<UserWrapper>>(userDao.getAllUser(), HttpStatus.OK);
            } else return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes (optional)
            return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMapping) {
        try{
            if(filter.isAdmin()){
                Optional<User> user=userDao.findById( Integer.parseInt(requestMapping.get("id")));
                if(user.isPresent()){
                    userDao.updateStatus(requestMapping.get("status"),user.get().getId());
                    //TODO:Add email functionality
//                    SendMailToAdmin(requestMapping.get("status"),user.get().getEmail(),userDao.getAllAdmins());
                    return CafeUtils.getResponseEntity("User Updated successfully", HttpStatus.OK);
                }
                else {
                    return CafeUtils.getResponseEntity("User Id does not exist", HttpStatus.OK);
                }
            }
            else {
                return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception e) {
            // Handle any exceptions that occur during authentication or token generation
            e.printStackTrace(); // Log the exception for debugging purposes (optional)
            return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMapping) {
        try{
            User user=userDao.findByEmailID(filter.getCurrentUser());
            if(user!=null){

                            if(user.getPassword().equals(requestMapping.get("oldPassword"))){
                                    if(!requestMapping.get("oldPassword").equals(requestMapping.get("newPassword"))){
                                        user.setPassword(requestMapping.get("password"));
                                        userDao.save(user);
                                        return CafeUtils.getResponseEntity("Password Updated successfully", HttpStatus.OK);
                                    }
                                    else{
                                        return CafeUtils.getResponseEntity("Current password and Old password are same", HttpStatus.BAD_REQUEST);
                                    }

                            }else{
                                return CafeUtils.getResponseEntity("Current Password is Incorrect", HttpStatus.BAD_REQUEST);
                            }
                }
                else {
                    return CafeUtils.getResponseEntity("user doesn't exist", HttpStatus.BAD_REQUEST);
                }

        }catch (Exception e) {
            // Handle any exceptions that occur during authentication or token generation
            e.printStackTrace(); // Log the exception for debugging purposes (optional)
            return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void SendMailToAdmin(String status, String email, List<UserWrapper> allAdmins) {
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
