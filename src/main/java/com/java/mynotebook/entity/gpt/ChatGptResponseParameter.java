package com.java.mynotebook.entity.gpt;


import java.util.List;

public class ChatGptResponseParameter {

    String id;
    String object;
    String created;
    String model;
    Usage usage;
    List<Choices> choices;

    public ChatGptResponseParameter() {
    }

    public ChatGptResponseParameter(String id, String object, String created,
                                    String model, Usage usage, List<Choices> choices) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.usage = usage;
        this.choices = choices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public List<Choices> getChoices() {
        return choices;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "ChatGptResponseParameter{" +
                "id='" + id + '\'' +
                ", object='" + object + '\'' +
                ", created='" + created + '\'' +
                ", model='" + model + '\'' +
                ", usage=" + usage +
                ", choices=" + choices +
                '}';
    }
}
