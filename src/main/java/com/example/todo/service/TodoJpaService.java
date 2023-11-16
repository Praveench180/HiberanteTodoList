/*
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.todo.repository.TodoJpaRepository;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoJpaService implements TodoRepository {
	@Autowired
	private TodoJpaRepository todoJpaRepository;

	@Override
	public ArrayList<Todo> getTodos() {
		List<Todo> list = todoJpaRepository.findAll();
		ArrayList<Todo> todos = new ArrayList<>(list);
		return todos;
	}

	@Override
	public Todo addTodo(Todo todo) {
		todoJpaRepository.save(todo);
		return todo;
	}

	@Override
	public Todo getTodoById(int todoid) {
		try {
			Todo newTodo = todoJpaRepository.findById(todoid).get();
			return newTodo;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Todo updateTodo(int todoid, Todo todo) {
		try {
			Todo newtodo = todoJpaRepository.findById(todoid).get();
			if (todo.getTodo() != null) {
				newtodo.setTodo(todo.getTodo());
			}
			if (todo.getPriority() != null) {
				newtodo.setPriority(todo.getPriority());
			}
			if (todo.getStatus() != null) {
				newtodo.setStatus(todo.getStatus());
			}
			todoJpaRepository.save(newtodo);
			return newtodo;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public void deleteTodo(int todoid) {
		try {
			todoJpaRepository.deleteById(todoid);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}