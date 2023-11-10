package com.java.mynotebook.swing.login;

import com.java.mynotebook.controller.api.UserApi;
import com.java.mynotebook.controller.impl.UserController;
import com.java.mynotebook.entity.User;
import com.java.mynotebook.utils.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用户的注册界面
 *
 * @author Tan JiangLin
 */
public class RegisterFrame extends JFrame {

    private UserApi userApi = new UserController();

    private JLabel labTitle = new JLabel("系统注册");
    private Font font = new Font("隶书", 0, 24);
    private JButton btnSure = new JButton("注册");
    private JButton btnCancel = new JButton("重置");
    private JButton btnLogin = new JButton("登录");
    private JPanel panBtn = new JPanel();

    private JLabel labLoginName = new JLabel("用户名:");
    private JLabel labPWD = new JLabel("密码:");
    private JLabel labPhone = new JLabel("电话:");
    private JTextField jtfLoginName, jtfPhone;
    private JPasswordField jpfPWD;
    private JPanel panMain = new JPanel();

    public RegisterFrame() {
        setTitle("用户注册界面");
        this.setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        add(labTitle, BorderLayout.NORTH);
        add(panBtn, BorderLayout.SOUTH);
        add(panMain, BorderLayout.CENTER);
        setVisible(true);
    }

    private void init() {
        labTitle.setFont(font);
        labTitle.setHorizontalAlignment(JLabel.CENTER);
        labTitle.setForeground(Color.RED);
        labTitle.setBackground(Color.BLUE);

        jtfLoginName = new JTextField("", 10);
        jpfPWD = new JPasswordField(16);
        jtfPhone = new JTextField("", 20);

        labLoginName.setSize(100, 80);
        labLoginName.setLocation(50, 20);
        labLoginName.setHorizontalAlignment(JLabel.RIGHT);

        labPWD.setSize(100, 80);
        labPWD.setLocation(50, 40);
        labPWD.setHorizontalAlignment(JLabel.RIGHT);

        labPhone.setSize(100, 80);
        labPhone.setLocation(50, 60);
        labPhone.setHorizontalAlignment(JLabel.RIGHT);

        jtfLoginName.setSize(150, 20);
        jtfLoginName.setLocation(160, 50);

        jpfPWD.setSize(150, 20);
        jpfPWD.setLocation(160, 70);
        jpfPWD.setEchoChar('*');

        jtfPhone.setSize(150, 20);
        jtfPhone.setLocation(160, 90);

        panBtn.add(btnSure);
        panBtn.add(btnCancel);
        panBtn.add(btnLogin);
        panMain.setLayout(null);

        panMain.add(labLoginName);
        panMain.add(labPWD);
        panMain.add(labPhone);

        panMain.add(jtfLoginName);
        panMain.add(jpfPWD);
        panMain.add(jtfPhone);

        btnSure.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // 获取用户输入的信息
                String name = jtfLoginName.getText().trim();
                String password = jpfPWD.getText().trim();
                String phone = jtfPhone.getText().trim();
                User inputUser = new User(name, password, phone);
                Result register = userApi.register(inputUser);
                if (register.getCode() == -1) {
                    JOptionPane.showMessageDialog(null, "用户已存在,请登录！");
                } else {
                    int i = register.getCode();
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "恭喜您，注册成功", "恭喜",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "注册失败", "失败",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                jtfLoginName.setText("");
                jpfPWD.setText("");
                jtfPhone.setText("");
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });
    }
}
