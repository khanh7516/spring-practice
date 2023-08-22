package com.example.totolist;

import com.example.totolist.database.TodoDB;
import com.example.totolist.ultis.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoListApplication implements CommandLineRunner {

	@Autowired
	private IFileReader iFileReader;

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TodoDB.todoList = iFileReader.readFile("E:/github/spring-practice/REST CRUD/to-to-list/src/main/resources/static/todo.xlsx");
	}
}
