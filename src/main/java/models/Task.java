package models;

import java.util.Date;

/**
 *
 * @author dagothar
 */
public class Task {

    private Long id;

    private Long authorId;

    private String todo;

    private Date date;

    public Task() {

    }

    public Task(Long id, Long authorId, String todo, Date date) {
        this.id = id;
        this.authorId = authorId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
