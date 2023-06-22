package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Stepan", "Shatalov", (byte) 23);
        userService.saveUser("Ivan", "Efimov", (byte) 24);
        userService.saveUser("Natasha", "Vasenkova", (byte) 26);
        userService.saveUser("Maxim", "Kuznetsov", (byte) 25);

        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
