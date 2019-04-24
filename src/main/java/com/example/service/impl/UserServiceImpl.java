package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void saveUser(User user) throws Exception {
        userMapper.insertUseGeneratedKeys(user);
    }

    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteUser(String userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public User queryUserById(String userId) {

        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> queryUserAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> queryUserListPaged(User user, Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public User queryUserByIdCustom(String userId) {
        return null;
    }

    @Override
    public void saveUserTransactional(User user) {

    }
}
