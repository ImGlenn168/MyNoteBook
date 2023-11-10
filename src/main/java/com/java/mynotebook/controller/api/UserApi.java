package com.java.mynotebook.controller.api;

import com.java.mynotebook.entity.User;
import com.java.mynotebook.utils.Result;

public interface UserApi {
    Result login(User inputUser);

    Result register(User inputUser);
}
