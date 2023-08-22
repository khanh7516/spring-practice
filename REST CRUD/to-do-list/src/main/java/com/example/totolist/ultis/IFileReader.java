package com.example.totolist.ultis;

import com.example.totolist.model.Todo;

import java.util.List;

public interface IFileReader {
    public List<Todo> readFile(String filePath);
}
