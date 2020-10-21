package com.wusd.familyfinanceapi.controller;

import com.wusd.familyfinanceapi.entity.gen.User;
import com.wusd.familyfinanceapi.mapper.gen.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam("userId") Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @PostMapping("")
    public User addUser(@RequestBody User user) {
        return user;
    }
}
