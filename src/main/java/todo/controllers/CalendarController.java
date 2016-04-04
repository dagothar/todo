package todo.controllers;

import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import todo.models.CalendarDay;
import todo.models.CurrentUser;
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
            Model m,
            Authentication auth
    ) {
        CurrentUser user = (CurrentUser) auth.getPrincipal();
        
        /* list of days for this month */
        LocalDate date = new LocalDate(year, month, 1);        
        List<List<CalendarDay>> weeks = calendarService.getWeeksList(user.getId(), date);
        m.addAttribute("weeks", weeks);
        
        /* for pagination */
        m.addAttribute("currentDate", new LocalDate());
        
        String title = String.format("%d %s", year, date.toString("MMM"));
        m.addAttribute("title", title);
        
        m.addAttribute("prevMonth", date.minusMonths(1).toString("yyyy/MM"));
        m.addAttribute("nextMonth", date.plusMonths(1).toString("yyyy/MM"));
        
        return "calendar";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String thisMonth() {
        LocalDate date = new LocalDate().withDayOfMonth(1);
        
        return String.format("redirect:/calendar/%s/%s", date.getYear(), date.getMonthOfYear());
    }
}
