package com.java.mynotebook.controller.impl;

import com.java.mynotebook.controller.api.UserApi;
import com.java.mynotebook.entity.User;
import com.java.mynotebook.service.UserService;
import com.java.mynotebook.utils.Result;

public class UserController implements UserApi {

    private UserService userService = new UserService();

    @Override
    public Result login(User inputUser) {
        User login = userService.login(inputUser);
        if (login != null) {
            return Result.success(login);
        }
        return Result.fail();
    }

    @Override
    public Result register(User inputUser) {
        return Result.getResult(userService.register(inputUser));
    }
}
