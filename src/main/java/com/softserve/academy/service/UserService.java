package com.softserve.academy.service;

import com.softserve.academy.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Override
    @Transactional
    public void createUser(User user) {

    }

    @Override
    @Transactional
    public void updateUser(User user) {

    }

    @Override
    @Transactional
    public void deleteUser(User user) {

    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return null;
    }

    @Override
    @Transactional
    public User loadUserByUsername(String username) {
        return null;
    }

    @Override
    @Transactional
    public List getAllUsers() {
        return null;
    }
}
