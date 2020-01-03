package in.rs.mdprogramming.todos.controller;

import in.rs.mdprogramming.todos.dto.TodoDTO;
import in.rs.mdprogramming.todos.exception.ResourceNotFoundException;
import in.rs.mdprogramming.todos.model.TodoStatus;
import in.rs.mdprogramming.todos.service.TodoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class TodoController {

    @Autowired
    private TodoServiceInterface todoService;

    @RequestMapping(method = RequestMethod.POST,
            path = "/todos",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void createTodo(@Valid @RequestBody TodoDTO todoDTO) throws ResourceNotFoundException {

        todoService.createTodo(todoDTO);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<TodoDTO> getTodos() {
        return todoService.getTodos();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/todos/{todoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public TodoDTO getTodo(@PathVariable Long todoId) throws ResourceNotFoundException {
        return todoService.getTodo(todoId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/todos/{todoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void editTodo(@PathVariable Long todoId, @Valid @RequestBody TodoDTO todoDTO) throws ResourceNotFoundException {
        todoService.editTodo(todoId, todoDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path ="/todos/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteTodo(@PathVariable Long todoId) throws ResourceNotFoundException {
        todoService.deleteTodo(todoId);
    }

    @RequestMapping(method = RequestMethod.PATCH, path ="/todos/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void changeStatus(@PathVariable Long todoId, @RequestBody TodoStatus status) throws ResourceNotFoundException {
        todoService.changeStatus(todoId, status);
    }
}
