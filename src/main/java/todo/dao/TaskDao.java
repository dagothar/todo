package todo.dao;

import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import todo.models.Task;

/**
 *
 * @author dagothar
 */
@Component
public interface TaskDao {

    public List<Task> findAll();

    public List<Task> findTasksByAuthorIdAndDate(Long authorId, LocalDate date);
    
    void setTaskStatus(Long id, boolean status);
    
    void removeTask(Long id);
    
    void addTask(Task task);

}
