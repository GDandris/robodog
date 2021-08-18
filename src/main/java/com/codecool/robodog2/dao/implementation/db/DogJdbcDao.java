package com.codecool.robodog2.dao.implementation.db;

import com.codecool.robodog2.dao.DogDAO;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class DogJdbcDao implements DogDAO {

    public Connection getConnection(){
        Connection connection = null;
        String userName = "sa";
        String password = "";


        try {
            connection = DriverManager.getConnection(
                    "jdbc:h2:~/robodogschool", userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public void addDog(Dog dog) {
        String sql = "INSERT INTO dog (breed, name, age) VALUES (?, ?, ?)";
        PreparedStatement st = null;
        try {
            st = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, dog.getBreed().name());
            st.setString(2, dog.getName());
            st.setInt(3, dog.getAge());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Dog> listDogs() {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM dog";
        List<Dog> dogs = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while(rs.next())
            {
                dogs.add(new Dog(Breed.valueOf(rs.getString(2)), rs.getString(4), rs.getInt(3)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dogs;
    }

    @Override
    public Dog getDog(long id) {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM dog WHERE id = "+id;
        Dog dog = null;
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if(rs.next())
            {
                dog = new Dog(Breed.valueOf(rs.getString(2)), rs.getString(4), rs.getInt(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dog;
    }

    @Override
    public void updateDog(Dog dog, long id) {
        Connection conn = getConnection();
        String sql = "UPDATE dog SET breed = ?, name = ?, age = ? WHERE id = ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, dog.getBreed().name());
            st.setString(2, dog.getName());
            st.setInt(3, dog.getAge());
            st.setLong(4, id);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void deleteDog(long id) {
        Connection conn = getConnection();
        String sql = "DELETE FROM dog WHERE id = "+id;
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        addDog(dog);
        Connection conn = getConnection();
        String SQL = "SELECT * FROM dog ORDER BY id DESC LIMIT 1";
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if(rs.next())
            {
                return rs.getLong(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  -1;
    }
}
