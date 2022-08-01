package com.example.utspt2072028graceag.util;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static java.sql.Connection getConnection() {
        java.sql.Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3303/utssa",
                    "root",
                    ""
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
