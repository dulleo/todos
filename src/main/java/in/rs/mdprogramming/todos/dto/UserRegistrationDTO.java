package in.rs.mdprogramming.todos.dto;

import javax.validation.constraints.*;
import java.util.Set;

public class UserRegistrationDTO extends UserLoginDTO {

    @NotNull(message="Property \'firstName\' is null!")
    @Size(min = 3, max = 50, message = "\'firstName\' property size must be min = 3 and max = 50 characters.")
    private String firstName;

    @NotNull(message="Property \'lastName\' is null!")
    @Size(min = 3, max = 50, message = "\'firstName\' property size must be min = 3 and max = 50 characters.")
    private String lastName;

    @NotEmpty(message="Property \'roles\' is null or empty!")
    private Set<String> roles;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

}
