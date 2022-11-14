package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String dbURL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String dbUserName = "root";
    private static final String dbPassword = "root";

    private static Connection connection;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        } catch (SQLException e) {
            System.out.println("Не удалось загрузить класс драйвера");
        }

        return connection;
    }
}
