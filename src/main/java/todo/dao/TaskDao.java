package todo.dao;

import todo.models.Task;

/**
 *
 * @author dagothar
 */
public interface TaskDao {
    
    Task findTaskById(Long id);
    
    void updateTask(Task task);
    
}
