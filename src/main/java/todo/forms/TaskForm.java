package todo.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author dagothar
 */
public class TaskForm {
    
    private boolean status;
    
    @NotNull
    @Size(min = 1, max = 255, message = "Task description has to be 1-255 characters long")
    private String todo;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public TaskForm() {
    }

    public TaskForm(boolean status, String todo, LocalDate date) {
        this.status = status;
        this.todo = todo;
        this.date = date;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
            
}
