package todo.services;

import java.util.List;
import todo.models.CurrentUser;
import todo.models.Task;

/**
 *
 * @author dagothar
 */
public interface TaskSecurityService {

    boolean isAuthor(CurrentUser user, Long author);

    public boolean isOwnerOf(CurrentUser user, Task task);

    public boolean isOwnerOf(CurrentUser user, List<Task> tasks);

    public boolean isOwnerOf(CurrentUser user, Long id);

}
