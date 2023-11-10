package com.java.mynotebook.swing.notebook;

import com.java.mynotebook.controller.api.NoteBookApi;
import com.java.mynotebook.controller.impl.NoteBookController;
import com.java.mynotebook.entity.NoteBook;
import com.java.mynotebook.swing.ai.ChatUI;
import com.java.mynotebook.swing.common.MsgFrame;
import com.java.mynotebook.swing.learn.WebLearnFrame;
import com.java.mynotebook.utils.Result;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NoteBookFrame extends JFrame {
    private NoteBookApi noteBookApi;
    private JTable jTable;
    private JScrollPane rightPane;

    private JPanel leftPane, iconPane;
    private JButton add, del, update, query, export, importFile, followAI, learn;

    private JTextField check;

    private JFileChooser jFileChooser;
    private NoteBookFrame noteBookFrame = this;

    public NoteBookFrame() {

        // 提供增删改查方法的对象
        noteBookApi = NoteBookController.getNoteBookController();
        // 载入数据
        this.setBounds(500, 200, 1000, 600);
        this.setTitle("Notebook");
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        this.addCom();
        this.showData(getList());
        this.setVisible(true);
    }


    /**
     * 主要控件
     */
    public void addCom() {
        jTable = new JTable();

        // icon
//        String imagePath = "E:\\WorkSpace\\Projects\\IdeaProjects\\MyNoteBook\\src\\main\\resources\\static\\image\\img.png";
//        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel label = new JLabel("notebook");
//        label.setIcon(imageIcon);
        iconPane = new JPanel();
        iconPane.setBounds(1, 1, 200, 55);
        iconPane.setBorder(new LineBorder(Color.GREEN));
        //将标签放入面板中
        iconPane.add(label);
        //将icon放入jframe界面
        getContentPane().add(iconPane);

        add = new JButton("添加");
        add.setBounds(60, 30, 80, 25);

        update = new JButton("修改");
        update.setBounds(60, 80, 80, 25);

        del = new JButton("删除");
        del.setBounds(60, 130, 80, 25);

        export = new JButton("导出");
        export.setBounds(60, 180, 80, 25);

        importFile = new JButton("导入");
        importFile.setBounds(60, 230, 80, 25);

        check = new JTextField();
        check.setBounds(50, 280, 100, 25);

        query = new JButton("查询");
        query.setBounds(60, 315, 80, 25);

        learn = new JButton("Learn");
        learn.setBounds(60, 375, 80, 30);

        followAI = new JButton("ChatGpt");
        followAI.setBounds(60, 420, 80, 30);


        // left
        leftPane = new JPanel();
        leftPane.setBounds(1, 60, 200, 510);
        leftPane.setBorder(new LineBorder(Color.PINK));
        leftPane.setLayout(null);
        //将标签放入面板中
        leftPane.add(add);
        leftPane.add(update);
        leftPane.add(del);
        leftPane.add(query);
        leftPane.add(export);
        leftPane.add(importFile);
        leftPane.add(followAI);
        leftPane.add(check);
        leftPane.add(learn);
        //将leftPane放入jframe界面
        getContentPane().add(leftPane);

        // right
        rightPane = new JScrollPane(jTable);
        rightPane.setBounds(203, 1, 790, 570);
        rightPane.setBorder(new LineBorder(Color.red));
        //这是第二个面板
        //将rightPane放入jframe界面
        getContentPane().add(rightPane);

        addListener();

    }

    public List<NoteBook> getList() {
        Result result = noteBookApi.getList();
        if (result.getCode() == 1) {
            List<NoteBook> data = (List<NoteBook>) result.getData();
            return data;
        }
        return new ArrayList<>();
    }


    // 不同的集合展示不同的数据
    public void showData(List<NoteBook> data) {
        // 居中显示数据
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // 设置表格数据模型
        jTable.setModel(NoteBookTableData.getModel(data));
        // 设置居中显示渲染器
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        // 表格数据变化时，修改数据
        jTable.getModel().addTableModelListener(e -> {
            noteBookApi.updateNoteBook(getCurrent());
        });
    }

    private void addListener() {
        addWords();
        updateWords();
        delWords();
        queryWords();
        exportWords();
        importWordsFile();
        followAI();
        webLearn();
        // 关闭窗口时，监听事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("system closed.....");
            }
        });
    }

    private void addWords() {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUpdateFrame(noteBookFrame, 1);
            }
        });
    }

    private void updateWords() {
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getCurrent() != null) {
                    new AddUpdateFrame(noteBookFrame, 2, getCurrent());
                }
                showData(getList());
            }
        });
    }

    private void delWords() {
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = jTable.getSelectedRows();
                List<Integer> ids = new ArrayList<>();
                for (int i : rows) {
                    int id = Integer.parseInt((String) jTable.getValueAt(i, 0));
                    ids.add(id);
                }
                noteBookApi.delNoteBook(ids);
                showData(getList());
            }
        });

    }

    private void queryWords() {
        query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = check.getText().trim();
                Result result = noteBookApi.queryNoteBooks(query);
                List<NoteBook> data = (List<NoteBook>) result.getData();
                showData(data);
            }
        });
    }

    private void exportWords() {
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFileChooser = new JFileChooser();
                int returnVal = jFileChooser.showSaveDialog(noteBookFrame);
                File currentDirectory = jFileChooser.getCurrentDirectory();
                // get filePath
                String absolutePath = currentDirectory.getAbsolutePath();
                // get fileName
                File selectedFile = jFileChooser.getSelectedFile();
                if (selectedFile != null) {
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        String fileName = selectedFile.getName();
                        String query = check.getText().trim();
                        Result result = noteBookApi.export(query, absolutePath, fileName);
                        if (!(boolean) result.getData()) {
                            new MsgFrame("导出失败！");
                        } else {
                            new MsgFrame("保存成功！");
                        }
                    }
                }
            }
        });
    }


    private void importWordsFile() {
        importFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFileChooser = new JFileChooser();
                // multi options，false means only choose one
                jFileChooser.setMultiSelectionEnabled(false);
                // filter，only display '.xlsx' type file
                jFileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File frame) {
                        return frame.getName().toLowerCase().endsWith(".xlsx");
                    }

                    @Override
                    public String getDescription() {
                        return ".xlsx";
                    }
                });
                int returnVal = jFileChooser.showOpenDialog(noteBookFrame);
                File file = jFileChooser.getSelectedFile();
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("selected file：" + file.getAbsolutePath());
                    noteBookApi.importFile(file);
                }
                showData(getList());
            }
        });
    }

    private void followAI() {
        followAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 打开AI学习窗口
                ChatUI chatUI = new ChatUI();
                chatUI.sendMessage();
                System.out.println("open AI window");
            }
        });
    }

    // 获取当前选中的数据对象
    public NoteBook getCurrent() {
        int row = jTable.getSelectedRow();
        if (row == -1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                new MsgFrame("请选择！");
                return null;
            }
        }
        return new NoteBook((String) jTable.getValueAt(row, 0), (String) jTable.getValueAt(row, 1),
                (String) jTable.getValueAt(row, 2), (String) jTable.getValueAt(row, 3),
                (String) jTable.getValueAt(row, 4), (String) jTable.getValueAt(row, 5));
    }

    private void webLearn() {
        learn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WebLearnFrame();
            }
        });
    }
}
