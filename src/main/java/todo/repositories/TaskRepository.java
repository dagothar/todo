package todo.repositories;

import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import todo.models.Task;

/**
 *
 * @author dagothar
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByAuthorAndDate(Long author, LocalDate date);
    
}
