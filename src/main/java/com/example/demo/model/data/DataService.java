package com.example.demo.model.data;

import com.example.demo.model.users.UserRepository;
import com.example.demo.model.users.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class DataService {
    private final DataRepository dataRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        Data data = findDatabyUUID(uuid).get(0);
        boolean exist = dataRepository.existsById(data.getId());
        if(!exist)
        {
            throw new IllegalStateException("Cannot find Data");
        }

        dataRepository.delete(data);
    }

    public void insertData(@NotNull PostData postData) throws IllegalStateException
    {
        UUID uuid;
        try {
            uuid = userService.findUserbyUsername(postData.getUsername()).get(0).getUuid();

        }catch (Exception e)
        {
            throw new IllegalStateException("Not Have this username");
        }
        if(uuid != null)
        {
            if(findDatabyUUID(uuid).size() > 0)
            {
                throw new IllegalStateException("User "+ postData.getUsername() +" Bio has duplicate");
            }

            List<String> bd = Arrays.asList(postData.getBirthday().split("-"));
            LocalDate birthday = LocalDate.of(new Integer(bd.get(2)),new Integer(bd.get(1)),new Integer(bd.get(0)));
            Data _data = new Data(uuid,postData.getUsername(),birthday,postData.getBio());
            dataRepository.save(_data);
        }

    }

    @Transactional
    public List<Data> findDatabyUUID(UUID uuid)
    {
        return jdbcTemplate.query("SELECT * FROM data_table d WHERE d.UUID='"+uuid.toString()+"'",new DataRowMapper());
    }

}
