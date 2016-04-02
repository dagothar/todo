package todo.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

/**
 *
 * @author dagothar
 */
@Component
public class UserForm {
    
    @NotNull
    private String username;
    
    @Size(min = 6, message = "Password has to be at least 6 characters long!")
    private String password;
    
    @Size(min = 6, message = "Password has to be at least 6 characters long!")
    private String passwordConfirm;

    public UserForm() {
    }

    public UserForm(String username, String password, String password2) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = password2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
}
