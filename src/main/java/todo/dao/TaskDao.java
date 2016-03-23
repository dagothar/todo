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

    public List<Task> findTasksByAuthorIdAndDate(int authorId, LocalDate date);
    
    void setTaskStatus(int id, boolean status);

}
