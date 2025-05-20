public class Main {

    public static void main(String[] args) {

        // Create SecureUser instances for testing
        SecureUser su = new SecureUser("Michelle", "hello123");

        // Testing password validation
        System.out.println("Is valid password? " + su.isValidPassword()); // true

        // Testing authentication with consecutive failed attempts
        System.out.println("1. " + su.authenticate("hello")); // false (incorrect password)
        System.out.println("2. " + su.authenticate("hello12")); // false (incorrect password)
        System.out.println("3. " + su.authenticate("Hello123")); // false (incorrect password, 3rd failure)

        // The account should be locked now
        System.out.println("4. " + su.authenticate("hello123")); // Account should be locked, returns false
    }
}
