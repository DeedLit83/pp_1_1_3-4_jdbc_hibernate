package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("FirstName1", "LastName1", (byte) 20);
        userService.saveUser("FirstName2", "LastName2", (byte) 21);
        userService.saveUser("FirstName3", "LastName3", (byte) 22);
        userService.saveUser("FirstName4", "LastName4", (byte) 23);

        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
