package com.example.demo.model.data;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class DataRowMapper implements RowMapper<Data> {

    @Override
    public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
        Data data = new Data();
        data.setId(rs.getLong("id"));
        data.setUuid(rs.getObject("uuid", UUID.class));
        data.setBio(rs.getString("bio"));
        data.setBirthday(rs.getObject("birt_day", LocalDate.class));
        data.setName(rs.getString("name"));
        return data;
    }
}
