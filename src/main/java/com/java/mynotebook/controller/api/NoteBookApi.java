package com.java.mynotebook.controller.api;

import com.java.mynotebook.entity.NoteBook;
import com.java.mynotebook.utils.Result;

import java.io.File;
import java.util.List;

public interface NoteBookApi {

    Result getList();


    Result addNoteBook(NoteBook noteBook);

    Result updateNoteBook(NoteBook noteBook);

    Result delNoteBook(List<Integer> ids);

    Result queryNoteBooks(String word);

    Result export(String param, String filePath, String fileName);

    Result importFile(File file);
}
