package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl userDao  = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException {
        userDao.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDao.saveUser(name, lastName, age);
        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    public void removeUserById(long id) throws SQLException {

        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
       return userDao.getAllUsers();
    }


    public void cleanUsersTable() throws SQLException {
        userDao.cleanUsersTable();
    }
}
