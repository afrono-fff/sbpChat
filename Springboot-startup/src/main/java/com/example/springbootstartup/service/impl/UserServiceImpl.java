package com.example.springbootstartup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootstartup.entities.User;
import com.example.springbootstartup.mapper.UserMapper;
import com.example.springbootstartup.service.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public int doLogin(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if(user != null){
            if(password.equals(user.getPassword())){
                // 登录成功
                return 0;
            }else{
                // 密码错误
                return 1;
            }
        }else{
            // 用户名不存在
            return 2;
        }
    }

    @Override
    public int doRegister(String username, String password, String confirm, String email) {
        if(userMapper.get_user_by_username(username) != null){
            // 用户名已存在
            return 1;
        }else{
            if(password.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}$")){
                // 符合规范
                if(password.equals(confirm)){
                    if(email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")){
                        User user = new User(username, password, email);
                        userMapper.insert(user);
                        return 0;
                    }else{
                        return 4;
                    }
                }else{
                    // 两次密码不一致
                    return 2;
                }
            }else{
                // 密码不合规范
                return 3;
            }
        }
    }
}
