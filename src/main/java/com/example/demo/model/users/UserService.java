package com.example.demo.model.users;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{
    private final UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    public void addUser(User user)
    {
        Optional<User> uuiDbyUsername = userRepository.findUUIDbyUsername(user.getUsername());
        if(uuiDbyUsername.isPresent())
        {
            throw new IllegalStateException("User Taken");
        }
        userRepository.save(user);
    }

    @Transactional
    public List<User> findAll()
    {
        return jdbcTemplate.query("Select * from user_data", new UserRowMapper());
    }

    @Transactional
    public List<User> findUserbyUsername(String username)
    {
        return jdbcTemplate.query("Select * from users_data where username=\'"+username+"\'",new UserRowMapper());
    }


}


