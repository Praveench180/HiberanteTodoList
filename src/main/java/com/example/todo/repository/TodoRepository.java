// Write your code here

package com.example.todo.repository;

import org.springframework.stereotype.Repository;
import com.example.todo.model.Todo;
import java.util.*;

@Repository
public interface TodoRepository {
    ArrayList<Todo> getTodos();

    Todo addTodo(Todo todo);

    Todo getTodoById(int todoid);

    Todo updateTodo(int todoid, Todo todo);

    void deleteTodo(int todoid);
}