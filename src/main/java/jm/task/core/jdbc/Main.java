package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        System.out.println("User с именем — LastName1 добавлен в базу данных");
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        System.out.println("User с именем — LastName2 добавлен в базу данных");
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        System.out.println("User с именем — LastName3 добавлен в базу данных");
        userDao.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println("User с именем — LastName4 добавлен в базу данных");

        List<User> allUsers = userDao.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user.toString());
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }


}
