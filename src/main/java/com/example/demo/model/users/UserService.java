package com.example.demo.model.users;

import com.example.demo.model.data.DataRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
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

    public void addUser(@NotNull Login login) throws IllegalStateException {
        List<User> findUsername = findUserbyUsername(login.getUsername());
        for(User u : findUsername)
            if(u.getUsername().equals(login.getUsername()))
            {
                throw new IllegalStateException("user taken");
            }
        userRepository.save(new User(login.getUsername(), login.getPassword()));
    }

    public UUID deleteUser(@NotNull String username) throws IllegalStateException
    {
        User user = findUserbyUsername(username).get(0);
        long id = user.getId();
        boolean exists = userRepository.existsById(id);
        if(!exists)
        {
            throw new IllegalStateException(username + " doesn't exist.");
        }
        userRepository.deleteById(id);
        return user.getUuid();
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


