package com.example.totolist.dao;

import com.example.totolist.model.Todo;

import java.util.List;

public interface TodoDAO {
    List<Todo> findAll();

    void save(Todo Todo);
}
