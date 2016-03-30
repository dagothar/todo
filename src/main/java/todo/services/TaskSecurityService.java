package todo.services;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;
import todo.models.CurrentUser;
import todo.models.Task;

/**
 *
 * @author dagothar
 */
@Component("taskSecurityService")
public class TaskSecurityService {
    
    public boolean isAuthor(CurrentUser user, Long author) {
        System.out.println(user.getId() + " " + author);
        return Objects.equals(user.getId(), author);
    }
    
    public boolean isOwnerOf(CurrentUser user, Task task) {
        return Objects.equals(user.getId(), task.getAuthor());
    }
    
    public boolean isOwnerOf(CurrentUser user, List<Task> tasks) {
        //System.out.println(user.getUsername() + "is owner of" + tasks.stream().noneMatch((task) -> (!isOwnerOf(user, task))));
        
        return tasks.stream().noneMatch((task) -> (!isOwnerOf(user, task)));
    }
}
