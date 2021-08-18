package com.codecool.robodog2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Robodog2Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Robodog2Application.class, args);
    }

}
