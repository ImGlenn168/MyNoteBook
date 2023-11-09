package com.java.mynotebook.controller;

import com.alibaba.excel.EasyExcel;
import com.java.mynotebook.entity.NoteBook;
import com.java.mynotebook.listener.EasyExcelListener;
import com.java.mynotebook.service.NoteBookService;
import com.java.mynotebook.utils.Result;

import java.io.File;
import java.util.List;

public class NoteBookController implements NoteBookApi {

    private static NoteBookController noteBookController;
    private final NoteBookService noteBookService = NoteBookService.getNoteBookService();

    public static synchronized NoteBookController getNoteBookController() {
        if (noteBookController == null) {
            noteBookController = new NoteBookController();
        }
        return noteBookController;
    }

    public Result getList() {
        return Result.success(noteBookService.getList());
    }

    @Override
    public Result updateNoteBook(NoteBook noteBook) {
        System.out.println("NoteBookController.updateNoteBook.........");
        return Result.getResult(noteBookService.updateNoteBook(noteBook));
    }

    @Override
    public Result addNoteBook(NoteBook noteBook) {
        System.out.println("NoteBookController.addNoteBook.........");
        return Result.getResult(noteBookService.addNoteBook(noteBook));
    }

    @Override
    public Result delNoteBook(List<Integer> ids) {
        System.out.println("NoteBookController.delNoteBook: " + ids.toString());
        return Result.getResult(noteBookService.delNoteBook(ids));
    }

    @Override
    public Result queryNoteBooks(String word) {
        return Result.success(noteBookService.queryNoteBooks(word));
    }

    @Override
    public Result export(String param) {
        return Result.success(noteBookService.export(param));
    }

    @Override
    public Result importFile(File file) {
        String fileName = file.getAbsolutePath();
        EasyExcel.read(fileName, NoteBook.class, new EasyExcelListener(noteBookService)).sheet().doRead();
        return Result.success();
    }
}
