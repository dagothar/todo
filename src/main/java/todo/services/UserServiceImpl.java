package todo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import todo.models.User;

/**
 *
 * @author dagothar
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> getUserById(Long id) {
        String sql = "SELECT * FROM Users WHERE id = ?";

        User user = null;
        try {
            user = (User) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(User.class));
        } catch (EmptyResultDataAccessException e) {

        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";

        User user = null;
        try {
            user = (User) jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper(User.class));
        } catch (EmptyResultDataAccessException e) {

        }

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
        
        return users;
    }

}