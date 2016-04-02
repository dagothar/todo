package todo.models;

/**
 *
 * @author dagothar
 */
public class User {
    
    private Long id;
    
    private String username;
    
    private String passwordHash;
    
    private Role role;
    
    public User() {
        
    }

    public User(Long id, String login, String passwordHash, Role role) {
        this.id = id;
        this.username = login;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s", id, username, passwordHash);
    }
    
}
