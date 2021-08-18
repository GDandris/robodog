package com.codecool.robodog2.dao.implementation.db;

import com.codecool.robodog2.dao.PedigreeDAO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import com.codecool.robodog2.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PedigreeJdbcDAO implements PedigreeDAO {

    @Autowired
    DogService dogService;

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
    public void addPedigree(Pedigree pedigree) {
        String sql = "INSERT INTO pedigree (puppy_id, mom_id, dad_id ) VALUES (?,?,?)";
        PreparedStatement st = null;
        try {
            st = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1, pedigree.getPuppyId());
            st.setLong(2, pedigree.getMomId());
            st.setLong(3, pedigree.getDadId());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Pedigree> listPedigrees() {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM pedigree";
        List<Pedigree> pedigrees = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while(rs.next())
            {
                pedigrees.add(new Pedigree(rs.getLong(3), rs.getLong(4), rs.getLong(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pedigrees;
    }

    @Override
    public Pedigree getPedigree(long id) {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM pedigree WHERE ID = "+id;
        Pedigree pedigree = null;
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if(rs.next())
            {
                pedigree = new Pedigree(rs.getLong(3), rs.getLong(4), rs.getLong(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pedigree;
    }

    @Override
    public void updatePedigree(long id, Pedigree pedigree) {
        Connection conn = getConnection();
        String sql = "UPDATE pedigree SET puppy_id = ?, mom_id = ?, dad_id = ? WHERE id = ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            st.setLong(1, pedigree.getPuppyId());
            st.setLong(2, pedigree.getMomId());
            st.setLong(3, pedigree.getDadId());
            st.setLong(4, id);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deletePedigree(long id) {
        Connection conn = getConnection();
        String sql = "DELETE FROM pedigree WHERE id = "+id;
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Dog> getFamily(long dogId) {
        List<Dog> family = new ArrayList<>();
        family.add(getDad(dogId));
        family.add(getMom(dogId));
        family.addAll(getSiblings(dogId));
        return family;
    }

    @Override
    public void makePuppy(long momId, long dadId, Dog puppy) {
        long puppyId = dogService.addDogAndReturnId(puppy);
        addPedigree(new Pedigree(momId,dadId,puppyId));
    }

    @Override
    public Dog getMom(long dogId) {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM pedigree WHERE puppy_id = "+dogId;
        Dog dog = null;
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if(rs.next())
            {
                dog = dogService.getDog(rs.getLong(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dog;
    }

    @Override
    public Dog getDad(long dogId) {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM pedigree WHERE puppy_id = "+dogId;
        Dog dog = null;
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if(rs.next())
            {
                dog = dogService.getDog(rs.getLong(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dog;
    }

    @Override
    public List<Dog> getSiblings(long dogId) {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM pedigree WHERE puppy_id = "+dogId;
        long[] parentIds = new long[2];
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if(rs.next())
            {
                parentIds[0] = rs.getLong(3);
                parentIds[1] = rs.getLong(4);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        SQL = "SELECT * FROM pedigree WHERE mom_id = "+parentIds[0] +" AND dad_id = " + parentIds[1];
        List<Dog> dogs = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SQL);
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while(rs.next())
            {
                if(rs.getLong(2) != dogId) {
                    dogs.add(dogService.getDog(rs.getLong(2)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dogs;
    }
}
