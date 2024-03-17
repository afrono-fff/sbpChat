package com.example.springbootstartup.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello, Springboot!!";
    }
}
