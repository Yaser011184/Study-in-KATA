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
    UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
        System.out.println("Таблица создана.");
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
        System.out.println("Таблица удалена.");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        System.out.println("Удален User c id " + id);
    }

    public List<User> getAllUsers() {
    System.out.println("Возвращены все Users");
       return userDao.getAllUsers();
}
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        System.out.println("Таблица Users очищена.");
    }
}
