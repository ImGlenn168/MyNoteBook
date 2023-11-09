package com.java.mynotebook.entity;

import com.alibaba.excel.annotation.ExcelProperty;

public class NoteBook {

    @ExcelProperty(value = "编号", index = 0)
    private String id;

    @ExcelProperty(value = "单词", index = 1)
    private String word;

    @ExcelProperty(value = "含义", index = 2)
    private String meaning;

    @ExcelProperty(value = "句子", index = 3)
    private String sentence;

    @ExcelProperty(value = "备注", index = 4)
    private String notes;

    @ExcelProperty(value = "创建时间", index = 5)
    private String createTime;

    public NoteBook() {
    }

    public NoteBook(String id, String word, String meaning, String sentence, String notes, String createTime) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.sentence = sentence;
        this.notes = notes;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "id='" + id + '\'' +
                ", word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                ", sentence='" + sentence + '\'' +
                ", notes='" + notes + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
