package com.example.totolist.service;

import com.example.totolist.dao.TodoDAO;
import com.example.totolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoDAO todoDAO;


    @Override
    public List<Todo> getAllTodos(Boolean status) {
        if (status == null) {
            return todoDAO.findAll();
        }
        return todoDAO.findAll().stream().filter(todo -> todo.isStatus() == status).collect(Collectors.toList());
    }

    @Override
    public Todo getTodoById(Integer id) {
        return todoDAO.findAll().stream().filter(todo -> todo.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Todo createTodo(Todo todo) {
        todo.setId(todoDAO.findAll().size());
        todo.setStatus(false);
        todoDAO.save(todo);
        return todo;
    }

    @Override
    public Todo updateTodo(Integer id, Todo todo) {
        Todo existingTodo = todoDAO.findAll().stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        if (existingTodo != null) {
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setStatus(todo.isStatus());
        }
        return existingTodo;
    }

    @Override
    public boolean deleteTodoById(Integer id) {
        return todoDAO.findAll().removeIf(todo -> todo.getId().equals(id));
    }
}
