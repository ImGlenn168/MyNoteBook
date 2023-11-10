package com.java.mynotebook.dao;

import com.java.mynotebook.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static List<User> userList = intUsers();

    private static List<User> intUsers() {
        userList = new ArrayList<>();
        User admin = new User("admin", "0000", 1);
        User aaa = new User("aaa", "123", 2);
        User user = new User("bbb", "123", 2);
        userList.add(admin);
        userList.add(aaa);
        userList.add(user);
        return userList;
    }

    public int addUser(User inputUser) {
        User registerUser = new User(inputUser.getUname(), inputUser.getPassword(), 2, inputUser.getTel());
        if (check(inputUser)) {
            return -1;
        }
        userList.add(registerUser);
        System.out.println(userList.toString());
        if (registerUser.getTel() != null) {
            return 1;
        }
        return -1;
    }

    private boolean check(User inputUser) {
        for (User user : userList) {
            if (inputUser.getUname().equals(user.getUname())) {
                return true;
            }
        }
        return false;
    }

    public User login(User inputUser) {
        for (User user : userList) {
            if (inputUser.getPassword().equals(user.getPassword()) && inputUser.getUname().equals(user.getUname())) {
                return user;
            }
        }
        return null;
    }
}
