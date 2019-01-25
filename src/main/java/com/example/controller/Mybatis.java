package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("mybatis")
public class Mybatis {
    @Autowired
    private UserService userService;

    @RequestMapping("/queryUser")
    public void queryUser(){
        String userId = "2";
        User user = userService.queryUserById(userId);
        System.out.println(user.getName());
//        System.out.println(userService.queryUserById(userId));
//        return userService.queryUserById(userId).getName();
    }
}
