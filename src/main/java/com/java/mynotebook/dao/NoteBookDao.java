package com.java.mynotebook.dao;

import com.java.mynotebook.entity.NoteBook;
import com.java.mynotebook.utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteBookDao {

    private static NoteBookDao noteBookDao;

    public static synchronized NoteBookDao getNoteBookDao() {
        if (noteBookDao == null) {
            noteBookDao = new NoteBookDao();
        }
        return noteBookDao;
    }

    private PreparedStatement pstmt;
    private int i;

    public List<NoteBook> getList() {
        List<NoteBook> noteBooks = new ArrayList<>();
        //?为占位符
        String sql = "select * from notebook ;";
        pstmt = DBUtil.getPreparedStatement(sql);
        try {
            // 分割出sql，取第二部分 select * from customer where cname = 'xxx' ;
            String[] split = pstmt.toString().split(":");
            String sql2 = split[1];
            ResultSet rs = pstmt.executeQuery(sql2); //执行查询语句
            while (rs.next()) {
                addNoteBookList(noteBooks, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noteBooks;
    }

    public int addNoteBook(NoteBook noteBook) {
        //?为占位符
        String sql = "insert into notebook(`word`, `meaning`, `sentence`, `notes`) values(?,?,?,?) ;";
        pstmt = DBUtil.getPreparedStatement(sql);
        try {
            pstmt.setString(1, noteBook.getWord());
            pstmt.setString(2, noteBook.getMeaning());
            pstmt.setString(3, noteBook.getSentence());
            pstmt.setString(4, noteBook.getNotes());
            // 分割出sql，取第二部分 select * from customer where cname = 'xxx' ;
            String[] split = pstmt.toString().split(":");
            String sql2 = split[1];
            i = pstmt.executeUpdate(sql2);//执行新增语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int updateNoteBook(NoteBook noteBook) {
        //?为占位符
        String sql = "update notebook set word=? , meaning=? , sentence=? , notes=? where id=?;";
        pstmt = DBUtil.getPreparedStatement(sql);
        try {
            pstmt.setString(1, noteBook.getWord());
            pstmt.setString(2, noteBook.getMeaning());
            pstmt.setString(3, noteBook.getSentence());
            pstmt.setString(4, noteBook.getNotes());
            pstmt.setString(5, noteBook.getId());
            // 分割出sql，取第二部分 select * from customer where cname = 'xxx' ;
            String[] split = pstmt.toString().split(":");
            String sql2 = split[1];
            i = pstmt.executeUpdate(sql2);//执行新增语句
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int delNoteBook(List<Integer> ids) {
        //?为占位符
        StringBuilder sql = new StringBuilder();
        sql.append("delete from notebook where id in ( ");
        for (Integer id : ids) {
            sql.append(id + ",");
        }
        sql.deleteCharAt(sql.toString().length() - 1);
        sql.append(" ) ; ");
        pstmt = DBUtil.getPreparedStatement(sql.toString());
        try {
            // 分割出sql，取第二部分 select * from customer where cname = 'xxx' ;
            String[] split = pstmt.toString().split(":");
            // 执行删除语句
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public List<NoteBook> queryNoteBooks(String word) {
        List<NoteBook> noteBooks = new ArrayList<>();
        //?为占位符
        String sql = "select * from notebook where word like ? or meaning like ? ;";
        pstmt = DBUtil.getPreparedStatement(sql);
        try {
            pstmt.setString(1, "%" + word + "%");
            pstmt.setString(2, "%" + word + "%");
            // 分割出sql，取第二部分 select * from customer where cname = 'xxx' ;
            String[] split = pstmt.toString().split(":");
            String sql2 = split[1];
            ResultSet rs = pstmt.executeQuery(sql2); //执行查询语句
            while (rs.next()) {
                addNoteBookList(noteBooks, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noteBooks;
    }

    private void addNoteBookList(List<NoteBook> customers, ResultSet rs) throws SQLException {
        NoteBook noteBook = new NoteBook();
        noteBook.setId(rs.getString("id"));
        noteBook.setWord(rs.getString("word"));
        noteBook.setMeaning(rs.getString("meaning"));
        noteBook.setSentence(rs.getString("sentence"));
        noteBook.setNotes(rs.getString("notes"));
        noteBook.setCreateTime(rs.getString("createTime"));
        customers.add(noteBook);
    }

    public void batchInsert(List<NoteBook> list) {
        //?为占位符
        String sql = "insert into notebook(`word`, `meaning`, `sentence`, `notes`) values(?,?,?,?) ;";
        try {
            pstmt = DBUtil.getPreparedStatement(sql);
            for (NoteBook noteBook : list) {
                pstmt.setString(1, noteBook.getWord());
                pstmt.setString(2, noteBook.getMeaning());
                pstmt.setString(3, noteBook.getSentence());
                pstmt.setString(4, noteBook.getNotes());
                // 分割出sql，取第二部分
                String[] split = pstmt.toString().split(":");
                String sql2 = split[1];
                // 加入执行队列
                pstmt.addBatch(sql2);
            }
            int i = 0;
            // 执行批处理队列
            int[] affectedRows = pstmt.executeBatch();
            // 打印受影响的行数
            for (int j : affectedRows) {
                i += j;
            }
            System.out.println(i + " rows added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
