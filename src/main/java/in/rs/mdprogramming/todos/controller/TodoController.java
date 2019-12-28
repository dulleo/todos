package in.rs.mdprogramming.todos.controller;

import in.rs.mdprogramming.todos.dto.TodoDTO;
import in.rs.mdprogramming.todos.exception.ResourceNotFoundException;
import in.rs.mdprogramming.todos.service.TodoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/")
public class TodoController {

    @Autowired
    private TodoServiceInterface taskService;

    @RequestMapping(method = RequestMethod.POST, path = "/todos", consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public void createTodo(@Valid @RequestBody TodoDTO todoDTO) throws ResourceNotFoundException {

        taskService.createTodo(todoDTO);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public List<TodoDTO> getTodos() {

        return taskService.getTodos();

    }

    @RequestMapping(method = RequestMethod.GET, path = "/todos/{todoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public TodoDTO getTodo(@PathVariable Long todoId) throws ResourceNotFoundException {

        return taskService.getTodo(todoId);

    }

    @RequestMapping(method = RequestMethod.PUT, path = "/todos/{todoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public void editTodo(@PathVariable Long todoId, @Valid @RequestBody TodoDTO todoDTO) throws ResourceNotFoundException {

        taskService.editTodo(todoId, todoDTO);

    }

    @RequestMapping(method = RequestMethod.DELETE, path =" /todos/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public void deleteTodo(@PathVariable Long todoId) throws ResourceNotFoundException {

        taskService.deleteTodo(todoId);

    }
}
