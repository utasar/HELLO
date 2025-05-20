public class SecureUser extends User {
    private int failedAttempts;

    public SecureUser(String username, String password) {
        super(username, password);
        this.failedAttempts = 0; // Initialize failed attempts to zero
    }

    @Override
    public boolean authenticate(String passwordAttempt) {
        if (failedAttempts >= 3) {
            return false; // Lock the user out after 3 failed attempts
        }

        // Check if the password attempt matches the actual password
        boolean isAuthenticated = super.authenticate(passwordAttempt);

        if (isAuthenticated) {
            // If successful, reset failed attempts
            failedAttempts = 0;
        } else {
            // Increment failed attempts if unsuccessful
            failedAttempts++;
        }

        return isAuthenticated;
    }
}
