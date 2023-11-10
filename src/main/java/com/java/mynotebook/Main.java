package com.java.mynotebook;


import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.java.mynotebook.swing.login.LoginFrame;

public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.install();
        new LoginFrame();
    }
}