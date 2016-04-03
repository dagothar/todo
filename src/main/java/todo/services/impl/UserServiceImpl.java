package todo.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import todo.forms.UserForm;
import todo.models.Role;
import todo.models.User;
import todo.services.UserService;

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

    @Override
    public void create(UserForm userForm) {
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(userForm.getPassword()));
        user.setRole(Role.USER);
        
        String sql = "INSERT INTO Users(username, passwordHash, role) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, new Object[] {user.getUsername(), user.getPasswordHash(), "USER"});
    }

}
