package com.example.service;

import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public void saveUser(User user) throws Exception;

    public User selectByName(String name);

    public void updateUser(User user);

    public void deleteUser(String userId);

    public User queryUserById(String userId);

    public List<User> queryUserList(User user);

    public List<User> queryUserListPaged(User user, Integer page, Integer pageSize);

    public User queryUserByIdCustom(String userId);

    public void saveUserTransactional(User user);
}
