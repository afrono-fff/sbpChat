package com.example.springbootstartup.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootstartup.entities.User;

public interface IUserService extends IService<User> {
    int doLogin(String username, String password);

    int doRegister(String username, String password, String confirm, String email);

}

