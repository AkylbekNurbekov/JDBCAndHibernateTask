package peaksoft;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

// удаление таблицы
//        userService.dropUsersTable();

// создание таблицы
//        userService.createUsersTable();
//
// заполнение таблицы
//        userService.saveUser("Talgar", "Yryskulov", (byte) 21);
//        userService.saveUser("Akylbek", "Nurbekov", (byte) 20);
//        userService.saveUser("Amantur", "Askarbekov", (byte) 20);
//        userService.saveUser("Aktan", "Omurzakov", (byte) 21);

//вывод на консоль все данные
        for (User l:userService.getAllUsers()) {
            System.out.println(l);

        }
//          userService.getAllUsers();

//удаление по id
//        userService.removeUserById(1);

//очистка таблицы
//        userService.cleanUsersTable();

    }
}
