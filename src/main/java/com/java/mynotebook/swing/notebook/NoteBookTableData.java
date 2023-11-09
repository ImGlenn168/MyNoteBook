package com.java.mynotebook.swing.notebook;

import com.java.mynotebook.entity.NoteBook;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class NoteBookTableData {
    public static DefaultTableModel getModel(List<NoteBook> customers) {
        String[] columnNames = new String[]{"编号", "单词", "含义", "句子", "备注", "创建时间"};

        String[][] data = new String[customers.size()][columnNames.length];
        for (int i = 0; i < customers.size(); i++) {
            data[i][0] = customers.get(i).getId();
            data[i][1] = customers.get(i).getWord();
            data[i][2] = customers.get(i).getMeaning();
            data[i][3] = customers.get(i).getSentence();
            data[i][4] = customers.get(i).getNotes();
            data[i][5] = customers.get(i).getCreateTime();
        }

        return new DefaultTableModel(data, columnNames);
    }
}
