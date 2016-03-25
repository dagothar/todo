package todo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import todo.models.CurrentUser;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String home(Authentication auth) {
        
        CurrentUser currentUser = (CurrentUser)auth.getPrincipal();
        System.out.println("id: " + currentUser.getId());
        
        return "home";
    }
    
}
