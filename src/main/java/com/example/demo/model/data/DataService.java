package com.example.demo.model.data;

import com.example.demo.model.users.User;
import com.example.demo.model.users.UserRepository;
import com.example.demo.model.users.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DataService {
    private final DataRepository dataRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public DataService(DataRepository dataRepository, UserRepository userRepository, UserService userService) {
        this.dataRepository = dataRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Data> getData(){
        return dataRepository.findAll();
    }

    public void deleteData(@NotNull UUID uuid)
    {
        Optional<Data> data = dataRepository.findDatabyUUID(uuid);
        boolean exist = dataRepository.existsById(data.get().getId());
        if(!exist)
        {
            throw new IllegalStateException("Cannot find Data");
        }

        dataRepository.delete(data.get());
    }

    public void insertData(PostData postData) throws IllegalStateException
    {
        UUID uuid = userService.findUserbyUsername(postData.getUsername()).get(0).getUuid();
        Optional<Data> data = dataRepository.findDatabyUUID(uuid);
        if(data.get().getUuid().equals(uuid))
        {
            throw new IllegalStateException("User "+ postData.getUsername() +"Bio has duplicate");
        }

        dataRepository.save(new Data(uuid,postData.getUsername(),postData.getBirthday(),postData.getBio()));

    }

}
