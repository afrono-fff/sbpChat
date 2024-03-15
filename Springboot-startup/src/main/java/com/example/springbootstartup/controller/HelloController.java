package com.example.springbootstartup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello, Springboot!!";
    }

    @PostMapping("/login")
    public String post(String username, String password){
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        return "登录信息";
    }
}
