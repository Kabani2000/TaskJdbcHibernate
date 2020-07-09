package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Aleksandr", "Bolotov", (byte) 35);
        userService.saveUser("Aleksandr", "Kabanov", (byte) 36);
        userService.saveUser("German", "Sevostyanov", (byte) 31);
        userService.saveUser("Neo", "Matrix", (byte) 100);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
