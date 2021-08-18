package com.codecool.robodog2.dao.implementation.db;

import com.codecool.robodog2.dao.TrickDAO;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Trick;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TrickDAOJdbc implements TrickDAO {

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
    public void addTrick(Trick trick) {
        String sql = "INSERT INTO trick (name) VALUES (?)";
        PreparedStatement st = null;
        try {
            st = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, trick.getName());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Trick> listTricks() {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM trick";
        List<Trick> tricks = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while(rs.next())
            {
                tricks.add(new Trick(rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tricks;
    }

    @Override
    public Trick getTrick(long id) {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM trick WHERE id = "+id;
        Trick trick = null;
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if(rs.next())
            {
                trick = new Trick(rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return trick;
    }

    @Override
    public void updateTrick(Trick trick, long id) {
        Connection conn = getConnection();
        String sql = "UPDATE trick SET name = ? WHERE id = ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, trick.getName());
            st.setLong(2, id);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteTrick(long id) {
        Connection conn = getConnection();
        String sql = "DELETE FROM trick WHERE id = "+id;
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
