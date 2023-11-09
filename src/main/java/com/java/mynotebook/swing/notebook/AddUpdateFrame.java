package com.java.mynotebook.swing.notebook;

import cn.hutool.core.util.StrUtil;
import com.java.mynotebook.controller.NoteBookApi;
import com.java.mynotebook.controller.NoteBookController;
import com.java.mynotebook.entity.NoteBook;
import com.java.mynotebook.swing.common.MsgFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class AddUpdateFrame extends JFrame {
    private NoteBookApi noteBookApi;

    private JLabel idLabel, wordLabel, meaningLabel, sentenceLabel, noteLabel;

    private JTextField id, word;

    private JTextArea meaning, sentence, notes;

    private JButton confirm, cancel;

    private NoteBook noteBook;

    private int type;

    private NoteBookFrame noteBookFrame;


    // 构造方法，若传入一个noteBook对象，则表示需要添加此对象。
    public AddUpdateFrame(NoteBookFrame noteBookFrame, int type) {
        // 提供增删改查方法的对象
        noteBookApi = NoteBookController.getNoteBookController();
        this.type = type;
        this.noteBookFrame = noteBookFrame;
        setBounds(700, 280, 400, 450);
        this.setTitle("添加笔记");
        // 载入数据
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        addCon();
        this.setVisible(true);
    }

    // 构造方法重载，若传入一个noteBook对象，则表示需要修改此对象。
    public AddUpdateFrame(NoteBookFrame noteBookFrame, int type, NoteBook noteBook) throws HeadlessException {
        this.noteBook = noteBook;
        this.type = type;
        this.noteBookFrame = noteBookFrame;
        noteBookApi = new NoteBookController();
        setBounds(700, 280, 400, 450);
        setTitle("修改笔记");
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        addCon();
        this.setVisible(true);
    }

    private void addCon() {
        idLabel = new JLabel("编号：");
        idLabel.setBounds(60, 25, 75, 30);
        id = new JTextField();
        id.setEditable(false);
        id.setBounds(110, 25, 200, 30);


        wordLabel = new JLabel("单词：");
        wordLabel.setBounds(60, 70, 75, 30);
        word = new JTextField();
        word.setBounds(110, 70, 200, 30);

        meaningLabel = new JLabel("含义：");
        meaningLabel.setBounds(60, 115, 75, 30);
        meaning = new JTextArea();
        meaning.setBounds(110, 115, 200, 60);


        sentenceLabel = new JLabel("句子：");
        sentenceLabel.setBounds(60, 190, 75, 30);
        sentence = new JTextArea();
        sentence.setBounds(110, 190, 200, 60);

        noteLabel = new JLabel("备注：");
        noteLabel.setBounds(60, 265, 75, 30);
        notes = new JTextArea();
        notes.setBounds(110, 265, 200, 60);


        confirm = new JButton("确定");
        confirm.setBounds(120, 360, 75, 30);
        cancel = new JButton("取消");
        cancel.setBounds(220, 360, 75, 30);

        if (type == 2) {
            id.setText(noteBook.getId());
            word.setText(noteBook.getWord());
            meaning.setText(noteBook.getMeaning());
            sentence.setText(noteBook.getSentence());
            notes.setText(noteBook.getNotes());
        }

        add(idLabel);
        add(wordLabel);
        add(meaningLabel);
        add(sentenceLabel);
        add(noteLabel);
        add(id);
        add(word);
        add(meaning);
        add(sentence);
        add(notes);
        add(confirm);
        add(cancel);

        addListener();
    }

    private void addListener() {
        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (type) {
                    case 1:
                        addWord();
                        break;
                    case 2:
                        updateWords();
                        break;
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void addWord() {
        if (!validation()) {
            return;
        }
        NoteBook noteBook = getNoteBook();
        noteBookApi.addNoteBook(noteBook);
        noteBookFrame.showData(noteBookFrame.getList());
        // 可能会多次添加，添加时不退出窗口
        // 点击取消退出窗口
//        dispose();
    }

    private void updateWords() {
        if (!validation()) {
            return;
        }
        NoteBook noteBook = getNoteBook();
        noteBookApi.updateNoteBook(noteBook);
        noteBookFrame.showData(noteBookFrame.getList());
        dispose();
    }

    private NoteBook getNoteBook() {
        NoteBook noteBook = new NoteBook();
        noteBook.setId(Optional.of(id.getText().trim()).orElse(""));
        noteBook.setWord(Optional.of(word.getText().trim()).orElse(""));
        noteBook.setMeaning(Optional.of(meaning.getText().trim()).orElse(""));
        noteBook.setSentence(Optional.of(sentence.getText().trim()).orElse(""));
        noteBook.setNotes(Optional.of(notes.getText().trim()).orElse(""));
        return noteBook;
    }

    // 用于验证数据是否为空
    private boolean validation() {
        boolean flag = true;
        String name = this.word.getText().trim();
        String tel = this.meaning.getText().trim();
        String weChat = this.sentence.getText().trim();
        if (StrUtil.isBlank(name) || StrUtil.isBlank(tel) || StrUtil.isBlank(weChat)) {
            flag = false;
            new MsgFrame("属性不能为空！");
            return flag;
        }
        return flag;
    }
}
