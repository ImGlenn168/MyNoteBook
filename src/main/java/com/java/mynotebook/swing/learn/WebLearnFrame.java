package com.java.mynotebook.swing.learn;

import com.java.mynotebook.swing.common.MsgFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;

public class WebLearnFrame extends JFrame {

    private JButton bbc, voa, ted, writing;
    private JPanel jPanel;

    private String BBC_URL = "www.baidu.com";
    private String VOA_URL = "www.baidu.com";
    private String TED_URL = "www.baidu.com";

    public WebLearnFrame() {

        // 载入数据
        this.setTitle("webLearn");
        this.setBounds(780, 280, 300, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        this.addCom();
        this.addListner();
        this.setVisible(true);
    }

    private void addCom() {
        // left
        jPanel = new JPanel();
        jPanel.setSize(300, 400);
        jPanel.setLayout(null);

        bbc = new JButton("bbc");
        bbc.setBounds(110, 30, 80, 50);
        voa = new JButton("voa");
        voa.setBounds(110, 110, 80, 50);
        ted = new JButton("ted");
        ted.setBounds(110, 190, 80, 50);
        writing = new JButton("writing");
        writing.setBounds(110, 270, 80, 50);

        jPanel.add(bbc);
        jPanel.add(voa);
        jPanel.add(ted);
        jPanel.add(writing);

        add(jPanel);

    }

    private void addListner() {
        openBBC();
        openVOA();
        openTED();
        doWriting();
    }

    private void openBBC() {
        bbc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(BBC_URL));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
    }

    private void openVOA() {
        voa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MsgFrame("功能暂未开放!");
            }
        });
    }

    private void openTED() {
        ted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MsgFrame("功能暂未开放!");
            }
        });
    }

    private void doWriting() {
        writing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MsgFrame("功能暂未开放!");
            }
        });
    }
}
