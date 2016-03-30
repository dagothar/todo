package todo.controllers;

import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import todo.services.CalendarService;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping(value = "/calendar")
public class CalendarController {
    
    @Autowired
    CalendarService calendarService;

    @RequestMapping(value = "/{year}/{month}", method = RequestMethod.GET)
    public String calendar(
            @PathVariable("year") int year,
            @PathVariable("month") int month,
            Model m
    ) {
        /* list of days for this month */
        LocalDate monthDate = new LocalDate(year, month, 1);
        List<LocalDate> daysOfTheMonth = calendarService.getDaysOfTheMonth(monthDate);
        m.addAttribute("days", daysOfTheMonth);
        
        /* for pagination */
        //String prevMonthHref = String.format();
        String title = String.format("%d %s", year, monthDate.toString("MMM"));
        
        return "calendar";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String thisMonth() {
        LocalDate date = new LocalDate().withDayOfMonth(1);
        
        return String.format("redirect:/calendar/%s/%s", date.getYear(), date.getMonthOfYear());
    }
}
