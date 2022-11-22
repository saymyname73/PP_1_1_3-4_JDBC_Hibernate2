package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection con = Util.getConnection();
             Statement statement = con.createStatement()) {

            String query = "CREATE TABLE IF NOT EXISTS USERS("
                    + "id INT AUTO_INCREMENT, "
                    + "name VARCHAR (40) NOT NULL, "
                    + "lastname VARCHAR (40) NOT NULL, "
                    + "age INT, "
                    + " PRIMARY KEY ( id ))";

            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection con = Util.getConnection();
             Statement statement = con.createStatement()) {

            statement.execute("DROP TABLE IF EXISTS USERS ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = Util.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?);")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection con = Util.getConnection();
             Statement statement = con.createStatement()) {

            statement.executeUpdate("DELETE FROM users WHERE id = " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection con = Util.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                byte age = resultSet.getByte(4);

                users.add(new User(name, lastname, age));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection con = Util.getConnection();
             Statement statement = con.createStatement()) {

            statement.executeUpdate("DELETE FROM users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
