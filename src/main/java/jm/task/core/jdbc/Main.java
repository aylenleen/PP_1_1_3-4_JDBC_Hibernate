package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        User u1 = new User("Иван", "Мешков", (byte) 25);
        User u2 = new User("Федор", "Собакин", (byte) 18);
        User u3 = new User("Василий", "Борщ", (byte) 30);
        User u4 = new User("Аркадий", "Поварешкин", (byte) 21);

        userService.saveUser(u1.getName(), u1.getLastName(), u1.getAge());
        userService.saveUser(u2.getName(), u2.getLastName(), u2.getAge());
        userService.saveUser(u3.getName(), u3.getLastName(), u3.getAge());
        userService.saveUser(u4.getName(), u4.getLastName(), u4.getAge());

        List<User> users = userService.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i)+"\n");
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
