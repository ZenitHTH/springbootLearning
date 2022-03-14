package com.example.demo.model.users;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class UserRowMapper implements RowMapper<User> {


    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUuid(rs.getObject("uuid", UUID.class));
        user.setUsername(rs.getString("username"));
        user.setHashPassword(rs.getString("hash_password"));
        user.setSalt(rs.getBytes("salt"));
        return user;
    }
}
