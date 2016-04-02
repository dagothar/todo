package todo.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import todo.forms.UserForm;
import todo.services.UserService;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {
    
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String register(Model m) {
        m.addAttribute("userForm", new UserForm());
        
        return "register";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public String processRegister(@Valid UserForm userForm, BindingResult result) {
        if (result.hasErrors()) {

            return "register";
        }
        
        userService.create(userForm);
        
        return "login";
    }

}
