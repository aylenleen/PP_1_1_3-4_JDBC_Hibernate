package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private Connection connection = null;
    private final String URL = "jdbc:mysql://localhost:3306/users_info";
    private final String USERNAME = "root";
    private final String PASSWORD = "kiviui12";

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
