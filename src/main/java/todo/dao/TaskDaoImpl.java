package todo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
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
    public Task findTaskById(Long id) {
        String sql = "SELECT * FROM Tasks WHERE id = ?";

        Task task = null;
        try {
            task = (Task) jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper() {
                
                @Override
                public Object mapRow(ResultSet rs, int i) throws SQLException {
                    Task task = new Task();
                    task.setId(rs.getLong("id"));
                    task.setAuthorId(rs.getLong("authorId"));
                    task.setStatus(rs.getBoolean("status"));
                    task.setTodo(rs.getString("todo"));
                    task.setDate(new LocalDate(rs.getString("date")));
                    
                    return task;
                } 
            });
        } catch (EmptyResultDataAccessException e) {
        }

        return task;
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
    
    
}
