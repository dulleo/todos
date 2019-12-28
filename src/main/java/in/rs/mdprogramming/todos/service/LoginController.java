package in.rs.mdprogramming.todos.service;

import in.rs.mdprogramming.todos.dto.UserLoginDTO;
import in.rs.mdprogramming.todos.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/auth")
public class LoginController {

    @Autowired
    private LoginServiceInterface loginService;

    @RequestMapping(method = RequestMethod.POST,
            path ="/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JwtResponse login(@Valid @RequestBody UserLoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }
}
