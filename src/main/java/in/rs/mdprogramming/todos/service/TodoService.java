package in.rs.mdprogramming.todos.service;

import in.rs.mdprogramming.todos.dto.TodoDTO;
import in.rs.mdprogramming.todos.exception.ResourceNotFoundException;
import in.rs.mdprogramming.todos.model.Todo;
import in.rs.mdprogramming.todos.model.TodoStatus;
import in.rs.mdprogramming.todos.model.User;
import in.rs.mdprogramming.todos.repository.TodoRepository;
import in.rs.mdprogramming.todos.repository.UserRepository;
import in.rs.mdprogramming.todos.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TodoService implements TodoServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    /**
     * Creates Todo in the database
     * @param todoDTO
     * @throws ResourceNotFoundException
     */
    @Override
    public void createTodo(TodoDTO todoDTO) throws ResourceNotFoundException {

        UserPrinciple principal = getUserPrincipal();

        User user = userRepository.findById(principal.getId()).orElseThrow(()
                -> new ResourceNotFoundException(String.format("User with id = %s does not !", principal.getId())));

        Todo todo = new Todo();
        todo.setDescription(todoDTO.getDescription());
        todo.setStatus(TodoStatus.OPEN);
        todo.setName(todoDTO.getName());
        todo.setTargetDate(todoDTO.getTargetDate());
        todo.setUser(user);

        todoRepository.save(todo);

    }

    /**
     * Returns all user's todos
     * @return
     */
    @Override
    public List<TodoDTO> getTodos() {

        UserPrinciple principal = getUserPrincipal();

        List<Todo> todos = todoRepository.findAllByUserId(principal.getId());
        List<TodoDTO> todoDTOs = new ArrayList<>();

        if(!todos.isEmpty()) {
            todos.stream().forEach(t -> {
                TodoDTO todoDTO = convertToDTO(t);
                todoDTOs.add(todoDTO);
            });
        }

        return todoDTOs;
    }

    /**
     * Returns required todo
     * @param todoId
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public TodoDTO getTodo(Long todoId) throws ResourceNotFoundException {

        Todo todo = getTodoFromDatabase(todoId);
        TodoDTO todoDTO = convertToDTO(todo);
        return todoDTO;

    }

    /**
     * Edit required todo
     * @param todoId
     * @param todoDTO
     * @throws ResourceNotFoundException
     */
    @Override
    public void editTodo(Long todoId, TodoDTO todoDTO) throws ResourceNotFoundException {

        Todo todo = getTodoFromDatabase(todoId);
        todo.setName(todoDTO.getName());
        todo.setDescription(todoDTO.getDescription());
        todo.setStatus(todoDTO.getStatus());
        todo.setTargetDate(todoDTO.getTargetDate());

        todoRepository.save(todo);

    }

    /**
     * Delete required todo
     * @param todoId
     * @throws ResourceNotFoundException
     */
    @Override
    public void deleteTodo(Long todoId) throws ResourceNotFoundException {

        Todo todo = getTodoFromDatabase(todoId);
        todoRepository.delete(todo);

    }

    /**
     *
     * @param todoId
     * @param status
     * @throws ResourceNotFoundException
     */
    @Override
    public void changeStatus(Long todoId, TodoStatus status) throws ResourceNotFoundException {

        Todo todo = getTodoFromDatabase(todoId);
        todo.setStatus(status);
        todoRepository.save(todo);

    }

    /**
     * Converts model to dto
     * @param t
     * @return
     */
    private TodoDTO convertToDTO(Todo t) {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setCreatedAt(t.getCreatedAt());
        todoDTO.setDescription(t.getDescription());
        todoDTO.setId(t.getId());
        todoDTO.setStatus(t.getStatus());
        todoDTO.setName(t.getName());
        todoDTO.setTargetDate(t.getTargetDate());
        return todoDTO;
    }

    /**
     * Returns todo from database
     * @param todoId
     * @return
     * @throws ResourceNotFoundException
     */
    private Todo getTodoFromDatabase(Long todoId) throws ResourceNotFoundException {

        UserPrinciple principal = getUserPrincipal();

        Todo todo = todoRepository.findByIdAndUserId(todoId, principal.getId());

        //this will cover situation if user require a task that does not belong to
        if(todo == null) {
            throw new ResourceNotFoundException(String.format("Todo id = %s related to the user id = %s, doesn't exist!", todoId, principal.getId()));
        }
        return todo;
    }

    /**
     * Returns user principal
     * @return
     */
    private UserPrinciple getUserPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();
        return principal;
    }
}
