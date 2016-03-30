package todo.services;

import java.util.List;
import org.joda.time.LocalDate;
import todo.models.CalendarDay;

/**
 *
 * @author dagothar
 */
public interface CalendarService {
    
    public List<LocalDate> getDaysBetween(LocalDate date1, LocalDate date2);
    
    public List<LocalDate> getDaysOfTheWeek(LocalDate date);
    
    public List<LocalDate> getDaysOfTheMonth(LocalDate date);
    
    public List<List<LocalDate>> getWeeksOfTheMonth(LocalDate date);
    
    public CalendarDay makeCalendarDay(Long author, LocalDate date, int month);
    
    public List<List<CalendarDay>> getWeeksList(Long author, LocalDate date);
    
}
