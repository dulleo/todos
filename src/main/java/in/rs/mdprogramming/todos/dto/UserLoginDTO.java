package in.rs.mdprogramming.todos.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginDTO {

    @NotBlank(message="Username is null!")
    @Size(max = 50, message = "Username size is max = 50 characters.")
    @Email(message="Username must be an email!")
    private String username;

    @NotNull(message="Password is null!")
    @Size(min = 8, max = 50, message = "Password size is min 8 and max 50 characters.")
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
