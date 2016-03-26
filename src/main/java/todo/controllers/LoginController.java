package todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String error, Model m) {
        if (error != null) {
            m.addAttribute("error", error);
        }
        
        return "login";
    }
}
