package com.java.mynotebook.entity.gpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choices {

    ChatGptMessage message;
    String finish_reason;
    Integer index;
}
