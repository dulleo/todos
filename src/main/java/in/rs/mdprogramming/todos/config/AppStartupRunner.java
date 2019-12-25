package in.rs.mdprogramming.todos.config;

import in.rs.mdprogramming.todos.model.Role;
import in.rs.mdprogramming.todos.model.RoleName;
import in.rs.mdprogramming.todos.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {

        List<Role> roles = roleRepository.findAll();

        if(roles.isEmpty()) {
            Role admin = new Role();
            admin.setName(RoleName.ROLE_ADMIN);
            roles.add(admin);

            Role user = new Role();
            user.setName(RoleName.ROLE_USER);
            roles.add(user);

            roleRepository.saveAll(roles);
        }
    }
}
