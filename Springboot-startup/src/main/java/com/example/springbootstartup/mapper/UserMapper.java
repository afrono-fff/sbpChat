package com.example.springbootstartup.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootstartup.entities.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
//    @Select("select * from user")
//    public List<User> get_all_user_info();
//
//    @Insert("insert into user values(#{id}, #{username}, #{password}, #{email})")
//    public int create_new_user(User user);

    @Select("select * from user where username=#{username}")
    public User get_user_by_username(String username);

    @Insert("insert into user values(#{id}, #{username}, #{password}, #{email})")
    public int create_new_user(User user);
}

