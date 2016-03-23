package todo.models;

import org.joda.time.LocalDate;

/**
 *
 * @author dagothar
 */
public class Task {

    private int id;

    private int authorId;

    private boolean status;

    private String todo;

    private LocalDate date;

    public Task() {

    }

    public Task(int id, int authorId, boolean status, String todo, LocalDate date) {
        this.id = id;
        this.authorId = authorId;
        this.status = status;
        this.todo = todo;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
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

}
