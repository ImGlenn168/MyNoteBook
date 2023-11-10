package com.java.mynotebook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int uid;
    private String uname;
    private String password;
    private int status;
    private String tel;

    public User(String uname, String password, int status, String tel) {
        this.uname = uname;
        this.password = password;
        this.status = status;
        this.tel = tel;
    }

    public User(String uname, String password, String tel) {
        this.uname = uname;
        this.password = password;
        this.tel = tel;
    }

    public User(String uname, String password, int status) {
        this.uname = uname;
        this.password = password;
        this.status = status;
    }
}
