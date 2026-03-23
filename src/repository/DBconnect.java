package repository;

import java.sql.*;

public class DBconnect {
    public static Connection getConnection() throws SQLException {

        final String URL = "jdbc:mysql://127.0.0.1:3306/hotel";
        final String USER = "root";
        final String PASSWORD = "12345";
        Connection connection;

        System.out.println("Попытка подключения к БД");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Подклюение к БД успешно");

        return connection;
        }
    }

