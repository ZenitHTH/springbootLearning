package com.example.demo.model.data;

import com.example.demo.model.users.User;
import com.example.demo.model.users.UserRepository;
import com.example.demo.model.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataConfig {

//    private final UserService userService;
//
//
//    public DataConfig(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    CommandLineRunner commandLineRunner(DataRepository repository) {
//        return args -> {
//
//            User useradmin = userService.findUserbyUsername("Admin").get(0);
//            Data theadmindata = new Data(
//                    1L,
//                    useradmin.getUuid(),
//                    useradmin.getUsername(),
//                    LocalDate.of(1997, 10, 24),
//                    "I am a admin"
//            );
//            repository.save(theadmindata);
//
//      };
//
//    }

}

