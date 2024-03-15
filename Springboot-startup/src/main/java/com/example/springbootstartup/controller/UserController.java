package com.example.springbootstartup.controller;

import com.example.springbootstartup.entities.User;
import com.example.springbootstartup.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired // 自动注入Mapper
    private UserMapper userMapper;

    @GetMapping("/get_all_user_info")
    public List get_all_user_info(){
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
        return userList;
    }
    @GetMapping("/get_user_by_id")
    public User get_user_by_id(int id){
        User user = userMapper.selectById(id);
        System.out.println(user);
        return user;
    }

    @PostMapping("/create_new_user")
    public String creat_new_user(User user){
        int i = userMapper.insert(user);
        if( i > 0){
            return "创建成功";
        }else{
            return "创建失败";
        }
    }
}
