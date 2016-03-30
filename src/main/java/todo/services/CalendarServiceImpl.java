package todo.services;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

/**
 *
 * @author dagothar
 */
@Service
public class CalendarServiceImpl implements CalendarService {

    @Override
    public List<LocalDate> getDaysOfTheMonth(LocalDate month) {
        List<LocalDate> days = new ArrayList<>();
        
        LocalDate day = month.withDayOfMonth(1);
        LocalDate lastDay = month.plusMonths(1);
        do {
            days.add(day);
            day = day.plusDays(1);
        } while (day.isBefore(lastDay));
        
        return days;
    }
    
    
}
