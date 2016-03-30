package todo.dao;

import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import todo.models.Task;

/**
 *
 * @author dagothar
 */
@Repository
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
    public List<Task> findTasksByAuthorAndDate(Long author, LocalDate date) {
        String sql = "SELECT * FROM Tasks WHERE author = ? AND date = ?";

        List<Task> tasks = jdbcTemplate.query(sql, new Object[]{author, date.toDate()}, new TaskRowMapper());

        return tasks;
    }
    
    

    @Override
    public void updateTask(Task task) {
        String sql = "UPDATE Tasks SET author = ?, todo = ?, status = ?, date = ? WHERE id = ?";
        
        Long id = task.getId();
        
        jdbcTemplate.update(sql, new Object[]{
            task.getAuthor(), 
            task.getTodo(), 
            task.getStatus(), 
            task.getDate().toString("yyyy-MM-dd"),
            id
        });
    }
    
    @Override
    public void deleteTask(Task task) {
        String sql = "DELETE FROM Tasks WHERE id = ?";
        
        jdbcTemplate.update(sql, new Object[]{task.getId()});
    }

    @Override
    public void createTask(Task task) {
        String sql = "INSERT INTO Tasks(author, status, todo, date) VALUES(?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                new Object[]{
                    task.getAuthor(),
                    task.getStatus(),
                    task.getTodo(),
                    task.getDate().toString("yyyy-MM-dd")
                }
        );
    }
    
    
}
