package com.javafxstockchart.service;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void getConnection() {
        //given
        //when
        //then
        assertNotNull(DatabaseConnection.getConnection());
    }
}