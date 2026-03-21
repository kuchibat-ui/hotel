package repository;

import java.sql.*;

public class DBconnect {
    public static Connection getConnection() throws SQLException{
        final String URL = "jdbc:mysql://127.0.0.1:3306/hotel";
        final String USER = "root";
        final String PASSWORD = "12345";

        Connection connection = null;



        try {

           return  DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}