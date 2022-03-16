package com.example.demo.model.users;

import com.example.demo.model.data.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="api/v1/users")
public class UsersController
{
    private final UserService userService;
    private final DataService dataService;
    @Autowired
    public UsersController(UserService userService, DataService dataService) {
        this.userService = userService;
        this.dataService = dataService;
    }

    @GetMapping
    public List<User> getUser()
    {
        return userService.getUsers();
    }

    @PostMapping("register")
    void registerNewUser(@RequestBody Login login)
    {
        userService.addUser(login);
    }

    @DeleteMapping(path="{username}")
    void deleteUser(@PathVariable("username") String username)
    {
        UUID uuid = userService.deleteUser(username);
        dataService.deleteData(uuid);
    }
}
