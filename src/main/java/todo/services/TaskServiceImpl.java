package todo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import todo.models.Task;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import todo.dao.TaskDao;
import todo.models.CurrentUser;

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
    public List<Task> getTasks(LocalDate date) {
        CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        List<Task> tasks = taskDao.findTasksByAuthorAndDate(user.getId(), date);
        
        return tasks;
    }

    @Override
    public void setTaskStatus(Long id, boolean status) {
        Task task = taskDao.findTaskById(id);
        CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (Objects.equals(task.getAuthorId(), user.getId())) {

            task.setStatus(status);
            taskDao.updateTask(task);
        } // else throw ...
    }

    @Override
    public void removeTask(Long id) {
        Task task = taskDao.findTaskById(id);
        CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (Objects.equals(task.getAuthorId(), user.getId())) {

            taskDao.deleteTask(id);
        } // else throw ...
    }

    @Override
    public void addTask(Task task) {
        CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        task.setAuthorId(user.getId());
        
        taskDao.createTask(task);
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
