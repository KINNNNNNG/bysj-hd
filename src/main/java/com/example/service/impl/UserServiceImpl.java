package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void saveUser(User user) throws Exception {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public User queryUserById(String userId) {

        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> queryUserList(User user) {
        return null;
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
