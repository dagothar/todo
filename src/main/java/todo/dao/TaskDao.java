package todo.dao;

import java.util.List;
import org.joda.time.LocalDate;
import todo.models.Task;

/**
 *
 * @author dagothar
 */
public interface TaskDao {
    
    List<Task> findAllTasks();
    
    Task findTaskById(Long id);
    
    List<Task> findTasksByAuthorAndDate(Long authorId, LocalDate date);
    
    void updateTask(Task task);
    
    void deleteTask(Task task);
    
    void createTask(Task task);
    
}
