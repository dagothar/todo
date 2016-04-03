package todo.services;

import java.util.List;
import todo.forms.UserForm;
import todo.models.User;

/**
 *
 * @author dagothar
 */
public interface UserService {

    User findUserById(Long id);

    User findUserByUsername(String username);

    List<User> findAllUsers();
    
    void create(UserForm userForm);
    
}
