package todo.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import todo.services.UserService;

/**
 *
 * @author dagothar
 */
@Component
public class UserFormValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UserForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserForm form = (UserForm) target;
        validatePassword(form, errors);
        validateUsername(form, errors);
    }

    private void validatePassword(UserForm form, Errors errors) {

        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "password.no_match", "Passwords do not match!");
        }
    }

    private void validateUsername(UserForm form, Errors errors) {

        if (userService.findUserByUsername(form.getUsername()) != null) {
            errors.rejectValue("username", "username.exists", "Username already exists!");
        }
    }

}
