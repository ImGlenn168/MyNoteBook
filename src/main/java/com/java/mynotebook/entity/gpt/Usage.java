package com.java.mynotebook.entity.gpt;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usage {

    String prompt_tokens;
    String completion_tokens;
    String total_tokens;
}
