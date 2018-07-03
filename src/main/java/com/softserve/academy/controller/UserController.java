package com.softserve.academy.controller;

import com.softserve.academy.model.User;
import com.softserve.academy.service.IUserService;
import com.softserve.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "api/user/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @RequestMapping(value = "api/user/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "api/user/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping(value = "api/user/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "api/user/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllUsers() throws IOException {
        return (List<User>) userService.getAllUsers();
    }
}