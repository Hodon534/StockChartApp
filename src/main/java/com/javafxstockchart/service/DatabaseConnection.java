package com.javafxstockchart.service;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Connect to local MySQL database
 * Url = 'jdbc:mysql://localhost:3306/'
 * Database = 'companies'
 * UserName = 'Hodon'
 * Password = 'Root1234'
 */
public class DatabaseConnection {

    /**
     * Connect to the database
     */
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/companies", "Hodon","Root1234" );
        } catch (Exception e) {
            System.out.println("Couldn't connect to the database.");
            return null;
        }
    }
}
