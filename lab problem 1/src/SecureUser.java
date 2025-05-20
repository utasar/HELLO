public class SecureUser extends User {

    private int failedAttempts;  // Field to keep track of consecutive failed authentications

    public SecureUser(String username, String password) {
        super(username, password);
        this.failedAttempts = 0;
    }

    @Override
    public boolean authenticate(String inputPassword) {
        // If there have been 3 consecutive failed attempts, lock the user out
        if (failedAttempts >= 3) {
            System.out.println("Account locked due to 3 consecutive failed attempts.");
            return false;
        }

        // Use the parent class's authenticate method to check if the password is correct
        if (super.authenticate(inputPassword)) {
            failedAttempts = 0;  // Reset the failed attempts on success
            return true;
        } else {
            failedAttempts++;  // Increment failed attempts
            return false;
        }
    }
}
