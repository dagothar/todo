package todo.models;

import java.util.List;
import org.joda.time.LocalDate;

/**
 *
 * @author dagothar
 */
public class CalendarMonth {
    
    private LocalDate date;
    
    private List<CalendarDay> days;
    
    private List<List<CalendarDay>> weeks;

    public CalendarMonth() {
    }

    public CalendarMonth(LocalDate date) {
        this.date = date.withDayOfMonth(1);
    }

    public List<CalendarDay> getDays() {
        return days;
    }

    public void setDays(List<CalendarDay> days) {
        this.days = days;
    }

    public List<List<CalendarDay>> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<List<CalendarDay>> weeks) {
        this.weeks = weeks;
    }
    
    
    
}
