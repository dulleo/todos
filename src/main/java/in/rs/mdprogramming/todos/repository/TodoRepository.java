package in.rs.mdprogramming.todos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserId(Long userId);
    Task findByIdAndUserId(Long userId, Long todoId);
}
