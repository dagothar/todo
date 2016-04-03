package todo.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import todo.forms.UserForm;
import todo.models.Role;
import todo.models.User;
import todo.repositories.UserRepository;
import todo.services.UserService;

/**
 *
 * @author dagothar
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        User user = userRepository.findOne(id);

        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);

        return user;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        
        return users;
    }

    @Override
    public void create(UserForm userForm) {
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(userForm.getPassword()));
        user.setRole(Role.USER);
        
        userRepository.save(user);
    }

}
