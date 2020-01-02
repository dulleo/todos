package in.rs.mdprogramming.todos.controller;

import in.rs.mdprogramming.todos.dto.UserLoginDTO;
import in.rs.mdprogramming.todos.response.JwtResponse;
import in.rs.mdprogramming.todos.service.LoginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class LoginController {

    @Autowired
    private LoginServiceInterface loginService;

    @RequestMapping(method = RequestMethod.POST,
            path ="/todos/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JwtResponse login(@Valid @RequestBody UserLoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }
}
