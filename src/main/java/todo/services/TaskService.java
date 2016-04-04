package todo.services;

import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import todo.forms.TaskForm;
import todo.models.Task;

/**
 *
 * @author dagothar
 */
@Component
public interface TaskService {

    //@PreAuthorize("hasRole('USER')")
    void addTask(TaskForm taskForm);

    @PostAuthorize("@taskSecurityService.isOwnerOf(principal, returnObject)")
    Task findTaskById(Long id);

    //@PreAuthorize("hasRole('USER')")
    @PostFilter("@taskSecurityService.isOwnerOf(principal, filterObject)")
    List<Task> findTasksByAuthorAndDate(Long author, LocalDate date);

    @PreAuthorize("@taskSecurityService.isOwnerOf(principal, #id)")
    void setTaskStatus(Long id, boolean status);
    
    @PreAuthorize("@taskSecurityService.isOwnerOf(principal, #id)")
    void pushTask(Long id);

    @PreAuthorize("@taskSecurityService.isOwnerOf(principal, #id)")
    void removeTask(Long id);

    int calculateProgress(List<Task> tasks);

}
