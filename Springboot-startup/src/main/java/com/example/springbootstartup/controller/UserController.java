package com.example.springbootstartup.controller;

import com.example.springbootstartup.entities.User;
import com.example.springbootstartup.mapper.UserMapper;
import com.example.springbootstartup.response.AppExceptionCodeMsg;
import com.example.springbootstartup.response.Response;
import com.example.springbootstartup.service.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired // 自动注入
    private IUserService iUserService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/user_signin")
    public Response signin(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        int status = iUserService.doLogin(username, password);
        if(status == 0){
            return Response.success("登录成功",username);
        }else if(status == 1){
            return Response.error(AppExceptionCodeMsg.WRONG_PASSWORD);
        }else{
            return Response.error(AppExceptionCodeMsg.USERNAME_NOT_EXISTS);
        }
    }

    @PostMapping("/user_signup")
    public Response signup(@RequestBody Map<String, String> request){
        String username = request.get("username");
        String password = request.get("password");
        String confirm = request.get("confirm");
        String email = request.get("email");
        int status = iUserService.doRegister(username, password, confirm, email);
        if(status == 0){
            return Response.success("注册成功",username);
        }else if(status == 1){
            return Response.error(AppExceptionCodeMsg.USERNAME_DUPLICATED);
        }else if(status == 2){
            return Response.error(AppExceptionCodeMsg.WRONG_CONFIRM);
        }else if(status == 3){
            return Response.error(AppExceptionCodeMsg.INCORRECT_FORMAT);
        }else{
            return Response.error(AppExceptionCodeMsg.WRONG_EMAIL);
        }
    }

    @GetMapping("/get_all_user_info")
    public Response<List<User>> get_all_user_info(){
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
        return Response.success("获取成功",userList);
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
