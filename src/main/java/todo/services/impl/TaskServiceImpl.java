package todo.services.impl;

import java.util.List;
import todo.models.Task;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import todo.forms.TaskForm;
import todo.models.CurrentUser;
import todo.repositories.TaskRepository;
import todo.services.TaskService;

/**
 *
 * @author dagothar
 */
@Service
public class TaskServiceImpl implements TaskService {
    
    @Autowired
    TaskRepository taskRepository;
    
    @Override
    public void addTask(TaskForm taskForm) {
        CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        Task task = new Task();
        task.setAuthor(user.getId());
        task.setDate(taskForm.getDate());
        task.setStatus(taskForm.getStatus());
        task.setTodo(taskForm.getTodo());
        
        taskRepository.save(task);
    }

    @Override
    public Task findTaskById(Long id) {
        Task task = taskRepository.findOne(id);

        return task;
    }

    @Override
    public List<Task> findTasksByAuthorAndDate(Long author, LocalDate date) {
        List<Task> tasks = taskRepository.findByAuthorAndDate(author, date);

        return tasks;
    }

    @Override
    public void setTaskStatus(Long id, boolean status) {
        taskRepository.setTaskStatus(status, id);
    }

    @Override
    public void pushTask(Long id) {
        Task task = taskRepository.findOne(id);
        
        Task newTask = new Task();
        newTask.setAuthor(task.getAuthor());
        newTask.setStatus(false);
        newTask.setTodo(task.getTodo());
        newTask.setDate(task.getDate().plusDays(1));
        
        taskRepository.save(newTask);
    }

    @Override
    public void removeTask(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public int calculateProgress(List<Task> tasks) {
        int completed = 0;
        for (Task task : tasks) {
            if (task.getStatus()) {
                ++completed;
            }
        }
        int n = tasks.size();
        int percentage = (int) (100.0 * completed / (n > 0 ? n : 1));

        return percentage;
    }

}
