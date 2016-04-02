package todo.services;

import java.util.List;
import java.util.Optional;
import todo.forms.UserForm;
import todo.models.User;

/**
 *
 * @author dagothar
 */
public interface UserService {

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    List<User> getAllUsers();
    
    void create(UserForm userForm);
    
}
