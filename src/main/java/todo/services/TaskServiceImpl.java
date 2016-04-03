package todo.services;

import java.util.List;
import todo.models.Task;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.repositories.TaskRepository;

/**
 *
 * @author dagothar
 */
@Service
public class TaskServiceImpl implements TaskService {
    
    @Autowired
    TaskRepository taskRepository;
    
    @Override
    public void addTask(Task task) {
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
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void removeTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public int calculatePercentageOfCompletedTasks(List<Task> tasks) {
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
