package in.rs.mdprogramming.todos.repository;

import in.rs.mdprogramming.todos.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByUserId(Long userId);
    Todo findByIdAndUserId(Long userId, Long todoId);
}
