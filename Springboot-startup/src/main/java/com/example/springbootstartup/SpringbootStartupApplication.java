package com.example.springbootstartup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springbootstartup.mapper")
public class SpringbootStartupApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStartupApplication.class, args);
    }

}
