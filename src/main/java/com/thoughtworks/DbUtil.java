package com.thoughtworks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static final String URL = "jdbc:mysql://localhost:3306/student_sys?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "950624";
    private static Connection conn = null;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return conn;
    }
}


