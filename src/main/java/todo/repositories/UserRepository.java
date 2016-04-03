package todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.models.User;

/**
 *
 * @author dagothar
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findUserByUsername(String username);
    
}
