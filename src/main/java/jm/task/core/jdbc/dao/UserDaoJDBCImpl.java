package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    private Statement statement;

    public UserDaoJDBCImpl() {
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Облом: " + e);
        }
    }

    public void createUsersTable() {
        try {
            statement.executeUpdate("CREATE TABLE users " +
                    "('id' INT NOT NULL AUTO_INCREMENT, " +
                    "'name' VARCHAR(45) NOT NULL , " +
                    "'lastName' VARCHAR(45) NOT NULL , " +
                    "age INT(3) NOT NULL )");
        } catch (SQLException e) {
            System.out.println("Не вышло: " + e);
        }
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("DROP TABLE if exists users");
        } catch (SQLException e) {
            System.out.println("Засада: " + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("План не сработал: " + e);
        }
    }

    public void removeUserById(long id) {
        try {
            statement.executeUpdate("DELETE FROM users WHERE Id = " + id);
        } catch (SQLException e) {
            System.out.println("Не тут-то было " + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            statement.execute("SELECT * FROM Users");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                list.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            System.out.println("Никак");;
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println("Хрен там: " + e);
        }
    }
}
