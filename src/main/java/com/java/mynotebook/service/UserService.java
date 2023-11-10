package com.java.mynotebook.service;

import com.java.mynotebook.dao.UserDao;
import com.java.mynotebook.entity.User;

public class UserService {

    private UserDao userDao = new UserDao();

    public int register(User inputUser) {
        return userDao.addUser(inputUser);
    }

    public User login(User inputUser) {
        User login = userDao.login(inputUser);
        if (login == null) {
            return null;
        }
        return userDao.login(inputUser);
    }
}
