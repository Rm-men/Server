package com.example.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public static Connection getConnectJDBC(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "123");
            System.out.println("Creating database connection...");
        } catch (SQLException e) {
            System.out.println("IN connection exception: " + e.getMessage());
        }
        return connection;
    }
}
