package todo.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import todo.forms.UserForm;
import todo.forms.UserFormValidator;
import todo.services.UserService;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {
    
    private UserService userService;
    private UserFormValidator userFormValidator;

    @Autowired
    public RegisterController(UserService userService, UserFormValidator userFormValidator) {
        this.userService = userService;
        this.userFormValidator = userFormValidator;
    }
    
    @InitBinder("userForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
    }

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
