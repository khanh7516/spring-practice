package com.example.totolist.dao;

import com.example.totolist.database.TodoDB;
import com.example.totolist.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoDAOImpl implements TodoDAO {

    @Override
    public List<Todo> findAll() {
        return TodoDB.todoList;
    }

    @Override
    public void save(Todo todo) {
        TodoDB.todoList.add(todo);
    }



}
