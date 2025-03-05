package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Util.getConnection();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        //System.out.println("User с именем — LastName1 добавлен в базу данных");
        userService.saveUser("Name2", "LastName2", (byte) 25);
        //System.out.println("User с именем — LastName2 добавлен в базу данных");
        userService.saveUser("Name3", "LastName3", (byte) 31);
        //System.out.println("User с именем — LastName3 добавлен в базу данных");
        userService.saveUser("Name4", "LastName4", (byte) 38);
        //System.out.println("User с именем — LastName4 добавлен в базу данных");

        List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }


}
