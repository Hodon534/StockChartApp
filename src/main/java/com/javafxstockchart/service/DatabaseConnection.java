package com.javafxstockchart.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/companies", "Hodon","Root1234" );
        } catch (Exception e) {
            System.out.println("Couldn't connect to the database.");
            return null;
        }
    }
}
