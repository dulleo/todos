package in.rs.mdprogramming.todos.service;

import in.rs.mdprogramming.todos.dto.UserRegistrationDTO;
import in.rs.mdprogramming.todos.exception.InvalidRoleException;
import in.rs.mdprogramming.todos.exception.UsernameExistsException;

public interface RegisterUserServiceInterface {

    void register(UserRegistrationDTO registerDTO) throws UsernameExistsException, InvalidRoleException;
}
