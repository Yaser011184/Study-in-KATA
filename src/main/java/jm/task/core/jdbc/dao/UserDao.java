package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age) throws SQLException;

    List<User> removeUserById(long id) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    void cleanUsersTable() throws SQLException;
}
