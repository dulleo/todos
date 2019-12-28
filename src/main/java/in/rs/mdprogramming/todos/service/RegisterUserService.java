package in.rs.mdprogramming.todos.service;

import in.rs.mdprogramming.todos.dto.UserRegistrationDTO;
import in.rs.mdprogramming.todos.exception.InvalidRoleException;
import in.rs.mdprogramming.todos.exception.UsernameExistsException;
import in.rs.mdprogramming.todos.model.Role;
import in.rs.mdprogramming.todos.model.RoleName;
import in.rs.mdprogramming.todos.model.User;
import in.rs.mdprogramming.todos.repository.RoleRepository;
import in.rs.mdprogramming.todos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RegisterUserService implements RegisterUserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void register(UserRegistrationDTO userRegistrationDTO) throws UsernameExistsException, InvalidRoleException {

        if (userRepository.existsByUsername(userRegistrationDTO.getUsername())) {
            throw new UsernameExistsException(String.format("Username \'%s\' already in use!", userRegistrationDTO.getUsername()));
        }

        // Creating user's account
        User user = new User(
                userRegistrationDTO.getFirstName(),
                userRegistrationDTO.getLastName(),
                userRegistrationDTO.getUsername(),
                encoder.encode(userRegistrationDTO.getPassword())
        );

        Set<Role> roles = new HashSet<>();

        for (String strRole : userRegistrationDTO.getRoles()) {

            switch (strRole) {
                case "admin":
                    Optional<Role> roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN);
                    if (roleAdmin != null) {
                        roles.add(roleAdmin.get());
                    }
                    break;

                case "user":
                    Optional<Role> roleUser = roleRepository.findByName(RoleName.ROLE_USER);
                    if (roleUser != null) {
                        roles.add(roleUser.get());
                    }
                    break;
                default:
                    throw new InvalidRoleException(String.format("Role \'%s\' is invalid!", strRole));
            }

        }

        user.setRoles(roles);
        userRepository.save(user);

    }
}
