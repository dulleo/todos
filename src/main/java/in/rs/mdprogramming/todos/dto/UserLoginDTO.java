package in.rs.mdprogramming.todos.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginDTO {

    @NotBlank(message="Property \'username\' is null!")
    @Size(max = 50, message = "\'username\' property size must be max = 50 characters.")
    @Email(message="Must be an email!")
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
