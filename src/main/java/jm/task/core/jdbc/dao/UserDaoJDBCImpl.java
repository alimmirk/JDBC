package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users(\n" +
            "                                        id SERIAL PRIMARY KEY,\n" +
            "                                        name VARCHAR(80) NOT NULL,\n" +
            "                                        last_name VARCHAR(100) NOT NULL,\n" +
            "                                        age TINYINT NOT NULL\n" +
            ");";
    private static final String ALL_USERS = "SELECT * FROM users";
    private static final String SAVE_USERS = "INSERT INTO users(name, last_name, age) VALUES(?, ?, ?)";
    private static final String CLEAN_USERS_TABLE = "DELETE FROM users";
    private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS users";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?";

    public UserDaoJDBCImpl() {}

    @Override
    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USERS_TABLE)) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DROP_USERS_TABLE)) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USERS)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("last_name");
                Byte age = rs.getByte("age");

                users.add(new User(id, name, lastName, age));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CLEAN_USERS_TABLE)) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
