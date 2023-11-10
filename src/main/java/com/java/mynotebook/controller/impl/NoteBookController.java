package com.java.mynotebook.controller.impl;

import com.alibaba.excel.EasyExcel;
import com.java.mynotebook.controller.api.NoteBookApi;
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
        return Result.getResult(noteBookService.updateNoteBook(noteBook));
    }

    @Override
    public Result addNoteBook(NoteBook noteBook) {
        return Result.getResult(noteBookService.addNoteBook(noteBook));
    }

    @Override
    public Result delNoteBook(List<Integer> ids) {
        return Result.getResult(noteBookService.delNoteBook(ids));
    }

    @Override
    public Result queryNoteBooks(String word) {
        return Result.success(noteBookService.queryNoteBooks(word));
    }

    @Override
    public Result export(String param, String path, String date) {
        return Result.success(noteBookService.export(param, path, date));
    }

    @Override
    public Result importFile(File file) {
        String fileName = file.getAbsolutePath();
        EasyExcel.read(fileName, NoteBook.class, new EasyExcelListener(noteBookService)).sheet().doRead();
        return Result.success();
    }
}
