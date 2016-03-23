package todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping(value = "/calendar")
public class CalendarController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String calendar() {
        return "calendar";
    }
}
