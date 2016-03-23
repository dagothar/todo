package todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }
    
}
