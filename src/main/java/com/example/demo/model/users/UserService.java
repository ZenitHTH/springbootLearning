package com.example.demo.model.users;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService
{

    public List<User> getUsers()
    {
        User user = new User(UUID.randomUUID(),"admin","password");
        List<User> list = new ArrayList<>();
        list.add(user);
        return list;
    }


}
