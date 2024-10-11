package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util connector = new Util();
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement statement = connector.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE Users (Id INT AUTO_INCREMENT PRIMARY KEY, Name NVARCHAR(20), " +
                    "LastName NVARCHAR(20), Age INT)");
        } catch (SQLException e) {

        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = connector.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE Users");
        } catch (SQLException e) {

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connector.getConnection().createStatement()) {
            statement.executeUpdate("INSERT INTO Users (Name, LastName, Age) " +
                    "VALUES ('" + name + "', '" + lastName + "', '" + age + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Statement statement = connector.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM Users WHERE Id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try (Statement statement = connector.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Users");
            while (result.next()) {
                User user = new User(result.getString(2),
                        result.getString(3), (byte) result.getInt(4));
                user.setId(Long.parseLong(result.getString(1)));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = connector.getConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
