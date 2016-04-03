package todo.services.impl;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import todo.models.CurrentUser;
import todo.models.Task;
import todo.repositories.TaskRepository;
import todo.services.TaskSecurityService;

/**
 *
 * @author dagothar
 */
@Component("taskSecurityService")
public class TaskSecurityServiceImpl implements TaskSecurityService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskSecurityServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public boolean isAuthor(CurrentUser user, Long author) {
        return Objects.equals(user.getId(), author);
    }

    @Override
    public boolean isOwnerOf(CurrentUser user, Task task) {
        return Objects.equals(user.getId(), task.getAuthor());
    }

    @Override
    public boolean isOwnerOf(CurrentUser user, List<Task> tasks) {
        return tasks.stream().noneMatch((task) -> (!isOwnerOf(user, task)));
    }

    @Override
    public boolean isOwnerOf(CurrentUser user, Long id) {
        Task task = taskRepository.findOne(id);

        return task.getAuthor().equals(user.getId());
    }
    
}
