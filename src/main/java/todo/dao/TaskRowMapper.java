package todo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.joda.time.LocalDate;
import org.springframework.jdbc.core.RowMapper;
import todo.models.Task;

/**
 *
 * @author dagothar
 */
public class TaskRowMapper implements RowMapper {

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

}
