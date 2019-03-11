package com.wu.service.impl;

import com.wu.dao.UserMapper;
import com.wu.pojo.User;
import com.wu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public User findOneUserByMail(String mail, String password) {
        return userMapper.getOneUserByMail(mail,password);
    }

    @Override
    public User findOneUserByPhone(String phoneNum, String password) {
        return userMapper.getOneUserByPhone(phoneNum,password);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int existMail(String mail) {
        return userMapper.existMail(mail);
    }

    @Override
    public int existPhone(String phone) {
        return userMapper.existPhone(phone);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUserInfo(user);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
