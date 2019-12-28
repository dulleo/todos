package in.rs.mdprogramming.todos.service;

import in.rs.mdprogramming.todos.dto.UserLoginDTO;
import in.rs.mdprogramming.todos.response.JwtResponse;

public interface LoginServiceInterface {

    JwtResponse login(UserLoginDTO loginDTO);
}
