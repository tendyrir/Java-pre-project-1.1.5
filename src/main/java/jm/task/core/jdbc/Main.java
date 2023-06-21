package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

//        Создание таблицы User(ов)
        userService.createUsersTable();
//        Добавление 4 User(ов) в таблицу с данными на свой выбор.
//        После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userService.saveUser("Stepan", "Shatalov", (byte) 23);
        userService.saveUser("Ivan", "Efimov", (byte) 24);
        userService.saveUser("Natasha", "Vasenkova", (byte) 26);
        userService.saveUser("Maxim", "Kuznetsov", (byte) 25);
//        Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user.toString());
        }
//        Очистка таблицы User(ов)
        userService.cleanUsersTable();
//        Удаление таблицы
        userService.dropUsersTable();
    }
}
