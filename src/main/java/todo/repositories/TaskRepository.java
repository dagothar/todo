package todo.repositories;

import java.util.List;
import javax.transaction.Transactional;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import todo.models.Task;

/**
 *
 * @author dagothar
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByAuthorAndDate(Long author, LocalDate date);
    
    @Transactional
    @Modifying
    @Query("UPDATE Task SET status = ? WHERE id = ?")
    int setTaskStatus(boolean status, Long id);
}
