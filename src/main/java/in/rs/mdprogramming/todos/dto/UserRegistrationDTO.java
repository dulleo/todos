package in.rs.mdprogramming.todos.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserRegistrationDTO {

    @NotNull(message="Property \'firstName\' is null!")
    @Size(min = 3, max = 50, message = "\'firstName\' property size must be min = 3 and max = 50 characters.")
    private String firstName;

    @NotNull(message="Property \'lastName\' is null!")
    @Size(min = 3, max = 50, message = "\'firstName\' property size must be min = 3 and max = 50 characters.")
    private String lastName;

    @NotBlank(message="Property \'email\' is null!")
    @Size(max = 50, message = "\'email\' property size must be max = 50 characters.")
    @Email(message="Must be an email!")
    private String email;

    @NotNull(message="Property \'roles\' is null!")
    private Set<String> roles;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<String> role) {
        this.roles = role;
    }
}
