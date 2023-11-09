package com.java.mynotebook.entity.gpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptRequestParameter {

    String model = "gpt-3.5-turbo";

    List<ChatGptMessage> messages = new ArrayList<>();

    double temperature = 0.7;

    public void addMessages(ChatGptMessage message) {
        this.messages.add(message);
    }

}
