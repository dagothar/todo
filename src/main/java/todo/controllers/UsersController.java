package todo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import todo.models.User;
import todo.services.UserService;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping(value = "/users")
public class UsersController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String users(Model m) {
        List<User> users = userService.getAllUsers();
        m.addAttribute("users", users);
        
        return "users";
    }
    
}
