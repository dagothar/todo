package todo.services;

import java.util.List;
import org.joda.time.LocalDate;

/**
 *
 * @author dagothar
 */
public interface CalendarService {
    
    public List<LocalDate> getDaysOfTheMonth(LocalDate month);
    
}
