package in.rs.mdprogramming.todos.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * This class represents user Role
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "name")
    private RoleName name;

    public RoleName getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Role{name =" + name + '}';
    }
}
