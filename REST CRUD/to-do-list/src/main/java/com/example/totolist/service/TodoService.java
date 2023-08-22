package com.example.totolist.service;

import com.example.totolist.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos(Boolean status);
    Todo getTodoById(Integer id);
    Todo createTodo(Todo todo);
    Todo updateTodo(Integer id, Todo todo);
    boolean deleteTodoById(Integer id);
}
