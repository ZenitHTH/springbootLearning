package com.example.demo.model;

import com.example.demo.model.data.Data;
import com.example.demo.model.data.DataRepository;
import com.example.demo.model.users.User;
import com.example.demo.model.users.UserRepository;
import com.example.demo.model.users.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class Config {

    private final UserService userService;

    public Config(UserService userService) {
        this.userService = userService;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userrepository, DataRepository dataRepository) {
        return args -> {
            User admin = new User(
                    1L,
                    UUID.randomUUID(),
                    "Admin",
                    "Password"
            );
            User peter = new User(2L, UUID.randomUUID(), "peter", "Runner01");
            List<User> list = new ArrayList<>();
            list.add(admin);
            list.add(peter);
            userrepository.saveAll(list);

            User useradmin = userService.findUserbyUsername("Admin").get(0);
            Data theadmindata = new Data(
                    1L,
                    useradmin.getUuid(),
                    useradmin.getUsername(),
                    LocalDate.of(1997, 10, 24),
                    "I am a admin"
            );
            dataRepository.save(theadmindata);

        };


    }

}