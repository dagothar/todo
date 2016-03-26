package todo.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author dagothar
 */
public class Task {

    private Long id;

    private Long authorId;

    private boolean status;

    @NotNull
    @Size(min = 1, max = 255, message = "Task description has to be 1-255 characters long")
    private String todo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Task() {

    }

    public Task(Long id, Long authorId, boolean status, String todo, LocalDate date) {
        this.id = id;
        this.authorId = authorId;
        this.status = status;
        this.todo = todo;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return String.format("Task[%d, %d, %b, %s, %s]", id, authorId, status, todo, date);
    }

}
