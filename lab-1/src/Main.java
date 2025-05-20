public class Main {
    public static void main(String[] args) {
        // Create a SecureUser object
        SecureUser su = new SecureUser("Michelle", "hello123");

        // Test isValidPassword
        System.out.println("isValidPassword: " + su.isValidPassword());  // Should return true

        // Test authenticate with incorrect password (3 failed attempts)
        System.out.println("Authenticate with 'hello': " + su.authenticate("hello")); // Should return false
        System.out.println("Authenticate with 'hello12': " + su.authenticate("hello12")); // Should return false
        System.out.println("Authenticate with 'Hello123': " + su.authenticate("Hello123")); // Should return false
        System.out.println("Authenticate with 'hello123': " + su.authenticate("hello123")); // Should return false (locked after 3 failed)

        // Test reset after successful authentication
        System.out.println("Authenticate with 'hello123' after lock: " + su.authenticate("hello123")); // Should return true
        System.out.println("Authenticate with 'hello12' again: " + su.authenticate("hello12")); // Should return false again
    }
}
