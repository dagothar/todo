package todo.models;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author dagothar
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;
    
    public CurrentUser(User user) {
        super(user.getUsername(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }
    
    public Role getRole() {
        return user.getRole();
    }
    
}
