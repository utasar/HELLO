
public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isValidPassword() {
        return password.length() >= 8 && !password.contains(username);
    }

    public boolean authenticate(String inputPassword) {
        return inputPassword.equals(password);
    }
}
