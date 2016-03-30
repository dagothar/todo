package todo.models;

import org.joda.time.LocalDate;

/**
 *
 * @author dagothar
 */
public class CalendarDay {
    
    private LocalDate date;
    
    private boolean busy;
    
    private boolean proper;

    public CalendarDay() {
    }

    public CalendarDay(LocalDate date, boolean status, boolean proper) {
        this.date = date;
        this.busy = status;
        this.proper = proper;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public boolean isProper() {
        return proper;
    }

    public void setProper(boolean proper) {
        this.proper = proper;
    }
}
