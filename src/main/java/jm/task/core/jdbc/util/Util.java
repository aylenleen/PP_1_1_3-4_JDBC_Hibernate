package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private Connection connection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/users_info";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "kiviui12";
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                Properties properties = new Properties();
                properties.setProperty("hibernate.connection.url", URL);
                properties.setProperty("dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
                properties.setProperty("hibernate.connection.username", USERNAME);
                properties.setProperty("hibernate.connection.password", PASSWORD);
                properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                properties.setProperty("hibernate.show_sql", "true");
                sessionFactory = new Configuration().addProperties(properties)
                        .addAnnotatedClass(User.class).buildSessionFactory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
