package com.softserve.academy.service;

import com.softserve.academy.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    public void createUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public User getUserById(int id);
    public User loadUserByUsername(String username);
    public List getAllUsers();
}
