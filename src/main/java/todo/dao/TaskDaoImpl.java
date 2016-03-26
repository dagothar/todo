package todo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import todo.models.Task;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 *
 * @author dagothar
 */
@Component
public class TaskDaoImpl implements TaskDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> findAll() {
        String sql = "SELECT * FROM Tasks";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        List<Task> tasks = new ArrayList<>();
        for (Map row : rows) {
            Task task = new Task(
                    Long.valueOf((int)row.get("id")),
                    Long.valueOf((int)row.get("authorId")),
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
                    Long.valueOf((int)row.get("id")),
                    Long.valueOf((int)row.get("authorId")),
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
        String sql = "UPDATE Tasks SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{status, id});
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

}
