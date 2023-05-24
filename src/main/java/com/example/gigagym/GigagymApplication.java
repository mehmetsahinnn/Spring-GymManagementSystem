package com.example.gigagym;

import com.example.gigagym.util.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class GigagymApplication {

    public static void main(String[] args) {
        SpringApplication.run(GigagymApplication.class, args);
    }

    @Bean
    public DBConnection dbConnection() {
        DBConnection dbConnection = new DBConnection();
        try {
            DBConnection.connect("jdbc:mysql://localhost:3306/gym?useSSL=false&serverTimezone=UTC", "", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}
