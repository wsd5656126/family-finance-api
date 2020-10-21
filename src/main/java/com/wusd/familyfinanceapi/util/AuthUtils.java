package com.wusd.familyfinanceapi.util;

import com.wusd.familyfinanceapi.entity.gen.User;
import com.wusd.familyfinanceapi.mapper.gen.UserMapper;
import com.wusd.familyfinanceapi.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {
    private static UserMapper userMapper;

    @Autowired
    public AuthUtils(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public static User getCurrUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetails userDetails = (MyUserDetails) principal;
        return userMapper.selectByPrimaryKey(userDetails.getUserId());
    }
}
