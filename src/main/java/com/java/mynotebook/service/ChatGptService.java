package com.java.mynotebook.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.java.mynotebook.entity.gpt.ChatGptMessage;
import com.java.mynotebook.entity.gpt.ChatGptRequestParameter;
import com.java.mynotebook.entity.gpt.ChatGptResponseParameter;
import com.java.mynotebook.entity.gpt.Choices;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ChatGptService {

    /**
     * 自己chatGpt的ApiKey
     */
    private String apiKey;

    /**
     * 对应的请求接口
     */
    private String url = "https://api.openai.com/v1/chat/completions";

    /**
     * 默认编码
     */
    private Charset charset = StandardCharsets.UTF_8;


    /**
     * 创建一个ChatGptRequestParameter，用于携带请求参数
     */
    private ChatGptRequestParameter chatGptRequestParameter = new ChatGptRequestParameter();

    /**
     * 相应超时时间，毫秒
     */
    private int responseTimeout = 1000;

    public void setResponseTimeout(int responseTimeout) {
        this.responseTimeout = responseTimeout;
    }

    public ChatGptService(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getAnswer(CloseableHttpClient client, String question) {

        // 创建一个HttpPost
        HttpPost httpPost = new HttpPost(url);

        // 设置请求参数
        chatGptRequestParameter.addMessages(new ChatGptMessage("user", question));

        // 创建一个JSONObject，用于解析和创建json
//        JSONObject requestJson = new JSONObject(chatGptRequestParameter);
        Object toJSON = JSON.toJSON(chatGptRequestParameter);

        System.out.println("请求参数：" + toJSON);
        HttpEntity httpEntity = null;
        try {
            // 对象转换为json字符串
            httpEntity = new StringEntity(toJSON.toString());
        } catch (Exception e) {
            System.out.println(question + "->json转换异常");
            return null;
        }
        httpPost.setEntity(httpEntity);


        // 设置请求头
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        // 设置登录凭证
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);

        // 用于设置超时时间
        RequestConfig config = RequestConfig
                .custom()
                .setResponseTimeout(responseTimeout, TimeUnit.MILLISECONDS)
                .build();
        httpPost.setConfig(config);
        try {
            // 提交请求
            return client.execute(httpPost, response -> {
                // 得到返回的内容
                String resStr = EntityUtils.toString(response.getEntity(), charset);
                Object resJSON = JSON.toJSON(resStr);
                // 转换为对象
                ChatGptResponseParameter responseParameter = JSONObject.toJavaObject((com.alibaba.fastjson.JSON) resJSON, ChatGptResponseParameter.class);
                String ans = "";
                // 遍历所有的Choices（一般都只有一个）
                for (Choices choice : responseParameter.getChoices()) {
                    ChatGptMessage message = choice.getMessage();
                    chatGptRequestParameter.addMessages(new ChatGptMessage(message.getRole(), message.getContent()));
                    String s = message.getContent().replaceAll("\n+", "\n");
                    ans += s;
                }
                // 返回信息
                return ans;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 发生异常，移除刚刚添加的ChatGptMessage
        chatGptRequestParameter.getMessages().remove(chatGptRequestParameter.getMessages().size() - 1);
        return "您当前的网络无法访问";
    }
}
