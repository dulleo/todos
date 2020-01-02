package in.rs.mdprogramming.todos.controller;

import in.rs.mdprogramming.todos.dto.UserRegistrationDTO;
import in.rs.mdprogramming.todos.exception.InvalidRoleException;
import in.rs.mdprogramming.todos.exception.UsernameExistsException;
import in.rs.mdprogramming.todos.service.RegisterUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegisterUserController {

    @Autowired
    private RegisterUserServiceInterface registerUserService;

    @RequestMapping(method = RequestMethod.POST, path = "/todos/auth/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) throws InvalidRoleException, UsernameExistsException {
        registerUserService.register(userRegistrationDTO);
    }
}
