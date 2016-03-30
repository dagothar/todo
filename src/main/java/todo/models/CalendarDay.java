package todo.models;

import org.joda.time.LocalDate;

/**
 *
 * @author dagothar
 */
public class CalendarDay {
    
    private LocalDate date;
    
    private boolean status;

    public CalendarDay() {
    }

    public CalendarDay(LocalDate date, boolean status) {
        this.date = date;
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
