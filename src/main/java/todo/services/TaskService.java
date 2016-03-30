package todo.services;

import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import todo.models.Task;

/**
 *
 * @author dagothar
 */
@Component
public interface TaskService {
    
    @PreAuthorize("hasRole('ADMIN') or @taskSecurityService.isOwnerOf(principal, #task)")
    void addTask(Task task);
    
    @PostAuthorize("hasRole('ADMIN') or @taskSecurityService.isOwnerOf(principal, returnObject)")
    Task findTaskById(Long id);

    @PreAuthorize("hasRole('ADMIN') or @taskSecurityService.isAuthor(principal, #author)")
    public List<Task> findTasksByAuthorAndDate(Long author, LocalDate date);
    
    @PreAuthorize("hasRole('ADMIN') or @taskSecurityService.isOwnerOf(principal, #task)")
    void updateTask(Task task);
    
    @PreAuthorize("hasRole('ADMIN') or @taskSecurityService.isOwnerOf(principal, #task)")
    void removeTask(Task task);

    int calculatePercentageOfCompletedTasks(List<Task> tasks);

}
