package todo.models;

import org.joda.time.LocalDate;

/**
 *
 * @author dagothar
 */
public class CalendarDay {
    
    private LocalDate date;
    
    private boolean busy;
    
    private int completed;
    
    private boolean proper;

    public CalendarDay() {
    }

    public CalendarDay(LocalDate date, boolean busy, int completed, boolean proper) {
        this.date = date;
        this.busy = busy;
        this.completed = completed;
        this.proper = proper;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isProper() {
        return proper;
    }

    public void setProper(boolean proper) {
        this.proper = proper;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
