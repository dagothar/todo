package todo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import todo.models.CurrentUser;
import todo.models.Task;

/**
 *
 * @author dagothar
 */
@Component
public class TaskDaoImpl implements TaskDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> findAllTasks() {
        String sql = "SELECT * FROM Tasks";
        
        List<Task> tasks = jdbcTemplate.query(sql, new TaskRowMapper());
        
        return tasks;
    }
    
    @Override
    public Task findTaskById(Long id) {
        String sql = "SELECT * FROM Tasks WHERE id = ?";

        Task task = null;
        try {
            task = (Task) jdbcTemplate.queryForObject(sql, new Object[]{id}, new TaskRowMapper());
            
        } catch (EmptyResultDataAccessException e) {
        }

        return task;
    }

    @Override
    public List<Task> findTasksByAuthorAndDate(Long authorId, LocalDate date) {
        String sql = "SELECT * FROM Tasks WHERE authorId = ? AND date = ?";

        List<Task> tasks = jdbcTemplate.query(sql, new Object[]{authorId, date.toDate()}, new TaskRowMapper());

        return tasks;
    }
    
    

    @Override
    public void updateTask(Task task) {
        String sql = "UPDATE Tasks SET authorId = ?, todo = ?, status = ?, date = ? WHERE id = ?";
        
        Long id = task.getId();
        
        jdbcTemplate.update(sql, new Object[]{
            task.getAuthorId(), 
            task.getTodo(), 
            task.getStatus(), 
            task.getDate().toString("yyyy-MM-dd"),
            id
        });
    }
    
    @Override
    public void deleteTask(Long id) {
        String sql = "DELETE FROM Tasks WHERE id = ?";
        
        jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public void createTask(Task task) {
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
