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
    public List<Task> findAll() {
        String sql = "SELECT * FROM Tasks";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        List<Task> tasks = new ArrayList<>();
        for (Map row : rows) {
            Task task = new Task(
                    Long.valueOf((int) row.get("id")),
                    Long.valueOf((int) row.get("authorId")),
                    (boolean) row.get("status"),
                    (String) row.get("todo"),
                    new LocalDate(row.get("date"))
            );
            tasks.add(task);
        }

        return tasks;
    }

    @Override
    public List<Task> findTasksByAuthorIdAndDate(Long authorId, LocalDate date) {
        String sql = "SELECT * FROM Tasks WHERE authorId = ? AND date = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{authorId, date.toDate()});

        List<Task> tasks = new ArrayList<>();
        for (Map row : rows) {
            Task task = new Task(
                    Long.valueOf((int) row.get("id")),
                    Long.valueOf((int) row.get("authorId")),
                    (boolean) row.get("status"),
                    (String) row.get("todo"),
                    new LocalDate(row.get("date"))
            );
            tasks.add(task);
        }

        return tasks;
    }

    @Override
    public List<Task> findTasksByAuthorId(Long authorId) {
        String sql = "SELECT * FROM Tasks WHERE authorId = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{authorId});

        List<Task> tasks = new ArrayList<>();
        for (Map row : rows) {
            Task task = new Task(
                    Long.valueOf((int) row.get("id")),
                    Long.valueOf((int) row.get("authorId")),
                    (boolean) row.get("status"),
                    (String) row.get("todo"),
                    new LocalDate(row.get("date"))
            );
            tasks.add(task);
        }

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
        String sql = "DELETE FROM Tasks WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public void addTask(Task task) {
        String sql = "INSERT INTO Tasks(authorId, status, todo, date) VALUES(?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                new Object[]{
                    task.getAuthorId(),
                    task.getStatus(),
                    task.getTodo(),
                    task.getDate().toString("yyyy-MM-dd")
                }
        );
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
