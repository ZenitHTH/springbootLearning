package com.example.demo.model.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class UsersConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository)
    {
        return args -> {
           User admin = new User(
                    1L,
                    UUID.randomUUID(),
                    "Admin",
                    "Password"
            );
           User peter = new User(2L,UUID.randomUUID(),"peter","Runner01");
           List<User> list = new ArrayList<>();
           list.add(admin);
           list.add(peter);
           repository.saveAll(list);
        };
    }

}
