package com.codecool.robodog2.dao.implementation.db;

import com.codecool.robodog2.dao.SkillDAO;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SkillJdbcDAO implements SkillDAO {

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
    public void addSkill(Skill skill) {
        String sql = "INSERT INTO skill (dog_id, trick_id, level ) VALUES (?,?,?)";
        PreparedStatement st = null;
        try {
            st = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1, skill.getDogId());
            st.setLong(2, skill.getTrickId());
            st.setInt(3, skill.getLevel());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Skill> listSkills() {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM skill";
        List<Skill> skills = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while(rs.next())
            {
                skills.add(new Skill(rs.getLong(2), rs.getLong(3), rs.getInt(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return skills;
    }

    @Override
    public Skill getSkill(long id) {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM skill WHERE ID = "+id;
        Skill skill = null;
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if(rs.next())
            {
                skill = new Skill(rs.getLong(2), rs.getLong(3), rs.getInt(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return skill;
    }

    @Override
    public void updateSkill(Skill skill, long id) {
        Connection conn = getConnection();
        String sql = "UPDATE skill SET dog_id = ?, trick_id = ?, level = ? WHERE id = ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            st.setLong(1, skill.getDogId());
            st.setLong(2, skill.getTrickId());
            st.setInt(3, skill.getLevel());
            st.setLong(4, id);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteSkill(long id) {
        Connection conn = getConnection();
        String sql = "DELETE FROM skill WHERE id = "+id;
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Dog> getDogsWithSkill(long trickId) {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM dog " +
                     "INNER JOIN skill " +
                     "ON dog.id = skill.dog_id " +
                     "WHERE skill.trick_id = "+trickId;
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
    public Optional<Skill> getSpecificSkill(long dogId, long trickId) {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM skill WHERE dog_id = "+dogId+" AND trick_id = " + trickId;
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if(rs.next())
            {
                Skill skill =  new Skill(rs.getLong(2), rs.getLong(3), rs.getInt(4));
                skill.setId(rs.getLong(1));
                return Optional.of(skill);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public long getLevelOfSkill(String trickName, long dogId) throws Exception {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM trick WHERE name = '"+trickName+"'";
        Long trickId = null;
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if(rs.next())
            {
                trickId =  rs.getLong(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(trickId == null){
            throw new Exception("Trick: " + trickName + ", doesn't exist");
        }
        Skill skill = null;
        if(getSpecificSkill(dogId, trickId).isPresent()){
            skill = getSpecificSkill(dogId, trickId).get();
        }
        else{
            throw new Exception("Specified skill doesn't exist");
        }
        return skill.getLevel();
    }

    @Override
    public void levelUpSkill(String trickName, long dogId) throws Exception {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM trick WHERE name = '"+trickName+"'";
        Long trickId = null;
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if(rs.next())
            {
                trickId =  rs.getLong(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(trickId == null){
            throw new Exception("Trick: " + trickName + ", doesn't exist");
        }
        Skill skill = null;
        if(getSpecificSkill(dogId, trickId).isPresent()){
            skill = getSpecificSkill(dogId, trickId).get();
            if(skill.getLevel() < 3){
                skill.setLevel(skill.getLevel()+1);
                updateSkill(skill,skill.getId());
            }
        }
        else{
            throw new Exception("Specified skill doesn't exist");
        }
    }
}
