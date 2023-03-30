package com.javafxstockchart.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

    public static Connection getConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/companies", "Hodon","Root1234" );
            return connection;
        } catch (Exception e) {
            System.out.println("Error" + e);
            return null;
        }
    }
}
