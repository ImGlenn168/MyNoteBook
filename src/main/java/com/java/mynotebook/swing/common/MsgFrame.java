package com.java.mynotebook.swing.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MsgFrame extends JFrame {
    private String msg;

    public MsgFrame(String msg) throws HeadlessException {
        setTitle("提示");
        this.msg = msg;
        this.setBounds(680, 380, 350, 130);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        JLabel jLabel = new JLabel(msg);
        jLabel.setBounds(120, 10, 200, 30);
        JButton button = new JButton("确定");
        button.setBounds(120, 50, 100, 30);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        add(button);
        add(jLabel);
        this.setVisible(true);
    }
}
