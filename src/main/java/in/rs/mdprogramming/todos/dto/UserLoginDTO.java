package in.rs.mdprogramming.todos.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginDTO {

    @NotNull(message="Property \'username\' is null!")
    @Size(min=3, max = 50, message = "\'username\' property size must be min = 3 and max = 50 characters.")
    @Email(message = "Property \'username\' must be an email!")
    private String username;

    @NotNull(message="Property \'password\' is null!")
    @Size(min = 8, max = 50, message = "\'password\' property size must be min = 8 and max = 50 characters.")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

    @Override
    public String toString() {
        return "[username=" + username + ", password=************]";
    }
}
