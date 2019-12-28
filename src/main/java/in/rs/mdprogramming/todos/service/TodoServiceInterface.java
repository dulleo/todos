package in.rs.mdprogramming.todos.service;

import in.rs.mdprogramming.todos.dto.TodoDTO;
import in.rs.mdprogramming.todos.exception.ResourceNotFoundException;

import java.util.List;

public interface TodoServiceInterface {

    void createTodo(TodoDTO todoDTO) throws ResourceNotFoundException;

    List<TodoDTO> getTodos();

    TodoDTO getTodo(Long todoId) throws ResourceNotFoundException;

    void editTodo(Long userId, TodoDTO taskDTO) throws ResourceNotFoundException;

    void deleteTodo(Long userId) throws ResourceNotFoundException;
}
