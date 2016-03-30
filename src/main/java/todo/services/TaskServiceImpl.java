package todo.services;

import java.util.List;
import todo.models.Task;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import todo.dao.TaskDao;

/**
 *
 * @author dagothar
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TaskDao taskDao;
    
    @Override
    public void addTask(Task task) {
        taskDao.createTask(task);
    }

    @Override
    public Task findTaskById(Long id) {
        Task task = taskDao.findTaskById(id);

        return task;
    }

    @Override
    public List<Task> findTasksByAuthorAndDate(Long authorId, LocalDate date) {
        List<Task> tasks = taskDao.findTasksByAuthorAndDate(authorId, date);

        return tasks;
    }

    @Override
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    @Override
    public void removeTask(Task task) {
        taskDao.deleteTask(task);
    }

    @Override
    public int calculatePercentageOfCompletedTasks(List<Task> tasks) {
        int completed = 0;
        for (Task task : tasks) {
            if (task.getStatus()) {
                ++completed;
            }
        }
        int n = tasks.size();
        int percentage = (int) (100.0 * completed / (n > 0 ? n : 1));

        return percentage;
    }

}
