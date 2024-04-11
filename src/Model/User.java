package Model;

public class User implements Comparable<User>{
    private String username;
    private String email;
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return STR."Username: \{this.username} | email: \{this.email}";
    }

    @Override
    public int compareTo(User o) {
        return this.username.compareTo(o.username);
    }
}
