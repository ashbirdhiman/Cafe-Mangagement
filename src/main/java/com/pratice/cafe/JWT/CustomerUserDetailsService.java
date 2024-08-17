package com.pratice.cafe.JWT;

import com.pratice.cafe.POJO.User;
import com.pratice.cafe.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerUserDetailsService implements UserDetailsService {


    UserDao userDao;

    User user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        log.info("Loading Username {}",username);
        user=userDao.findByEmailID(username);
        if(!Objects.isNull(user)){
            return new org.springframework.security.core.
                    userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
        }
        else throw new UsernameNotFoundException("User Not found");
    }
    public User getUserDetails(){
        return user;
    }
}
