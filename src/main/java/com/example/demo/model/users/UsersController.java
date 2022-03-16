package com.example.demo.model.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/users")
public class UsersController
{
    private final UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUser()
    {
        return userService.getUsers();
    }

    @PostMapping
    void registerNewUser(@RequestBody Login login)
    {
        User user = new User(login.getUsername(), login.getPassword());
        userService.addUser(user);
    }
}
