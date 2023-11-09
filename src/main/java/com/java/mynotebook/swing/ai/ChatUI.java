package com.java.mynotebook.swing.ai;


import cn.hutool.core.util.StrUtil;
import com.java.mynotebook.service.ChatGptService;
import com.java.mynotebook.swing.common.MsgFrame;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ChatUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private String question, answer;

    public ChatUI() {


        setTitle("聊天窗口");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocation(700, 100);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputPanel.add(inputField, BorderLayout.CENTER);

        sendButton = new JButton("发送");
        inputPanel.add(sendButton, BorderLayout.EAST);
        getRootPane().setDefaultButton(sendButton);

        panel.add(inputPanel, BorderLayout.SOUTH);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // 用于验证数据是否为空
    public boolean validation() {
        boolean flag = true;
        String message = this.inputField.getText().trim();
        if (StrUtil.isBlank(message)) {
            flag = false;
            new MsgFrame("消息不能为空！");
            return flag;
        }
        return flag;
    }

    private String sendMessageToGpt() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String apiKey = "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";
        ChatGptService customChatGpt = new ChatGptService(apiKey);
        // 根据自己的网络设置吧
        customChatGpt.setResponseTimeout(20000);
        question = inputField.getText();
        long start = System.currentTimeMillis();
        answer = customChatGpt.getAnswer(httpClient, question);
        long end = System.currentTimeMillis();
        System.out.println("该回答花费时间为：" + (end - start) / 1000.0 + "秒");
        System.out.println(answer);
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public void sendMessage() {

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validation()) {
                    return;
                }
                chatArea.append("\n");
                chatArea.append("  me" + ":  " + inputField.getText().trim());
                System.out.println(inputField.getText().trim());
                chatArea.append("\n");
                chatArea.append("  gpt" + ":  " + sendMessageToGpt());
                chatArea.append("\n");
                inputField.setText("");
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatUI window = new ChatUI();
            window.sendMessage();
        });
    }
}
