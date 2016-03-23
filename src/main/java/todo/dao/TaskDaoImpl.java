package todo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import todo.models.Task;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
                    (int) row.get("id"),
                    (int) row.get("authorId"),
                    (boolean) row.get("status"),
                    (String) row.get("todo"),
                    new LocalDate(row.get("date"))
            );
            tasks.add(task);
        }

        return tasks;
    }

    @Override
    public List<Task> findTasksByAuthorIdAndDate(int authorId, LocalDate date) {
        String sql = "SELECT * FROM Tasks WHERE authorId = ? AND date = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{authorId, date.toDate()});

        List<Task> tasks = new ArrayList<>();
        for (Map row : rows) {
            Task task = new Task(
                    (int) row.get("id"),
                    (int) row.get("authorId"),
                    (boolean) row.get("status"),
                    (String) row.get("todo"),
                    new LocalDate(row.get("date"))
            );
            tasks.add(task);
        }

        return tasks;
    }

    @Override
    public void setTaskStatus(int id, boolean status) {
        String sql = "UPDATE Tasks SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{status, id});
    }

    @Override
    public void removeTask(int id) {
        String sql = "DELETE FROM Tasks WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

}
