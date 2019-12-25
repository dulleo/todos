package in.rs.mdprogramming.todos.repository;

import in.rs.mdprogramming.todos.model.Role;
import in.rs.mdprogramming.todos.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);

}
