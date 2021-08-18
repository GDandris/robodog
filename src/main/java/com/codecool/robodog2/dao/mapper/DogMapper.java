package com.codecool.robodog2.dao.mapper;

import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DogMapper implements RowMapper<Dog> {
    @Override
    public Dog mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dog dog = new Dog();
        dog.setId(rs.getLong("id"));
        dog.setBreed(Breed.valueOf(rs.getString("breed")));
        dog.setAge(rs.getInt("age"));
        dog.setName(rs.getString("name"));
        return dog;
    }
}
