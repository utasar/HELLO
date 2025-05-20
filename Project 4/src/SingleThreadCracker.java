//Code is java doc by chatgpt
//all take help from friends computer help for processing
// if in case for anymisuse from friend side i share my code to gaurav , muhhamd
//ta in math learning centre help me in coding step and all checking in
//threding all i got to know my code works well on 13 from mohammad and again
//i just search in google oracle for concept and used chagpt for multithreading  mustly i did by help of kiran bro
// other wise it was easy i was blank
//
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * SingleThreadCracker
 *
 * Utsav Acharya
 * CS 1181 - Project 4 - Part 1
 *
 * A simple brute-force password cracker for ZIP files.
 * It tries all possible 3-letter lowercase passwords (a-z)
 * recursively until it finds the correct password.
 */
public class SingleThreadCracker {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz"; // allowed characters
    private static final int PASSWORD_LENGTH = 3; // password must be exactly 3 characters
    private static boolean passwordFound = false; // flag to stop recursion once found

    /**
     * Main method to start the cracking process.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        crack("", 0, "protected3.zip", "super_secret_output.txt");
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " ms");
    }

    /**
     * Recursive method to generate all possible passwords
     * and try to crack the ZIP file.
     *
     * @param current    the currently built password string
     * @param depth      the current depth in recursion (how many characters filled)
     * @param zipFile    the path to the zip file to crack
     * @param outputPath the path where extracted contents will be saved
     */
    private static void crack(String current, int depth, String zipFile, String outputPath) {
        if (passwordFound) return; // stop if already found

        // Base case: if the password has reached the required length
        if (depth == PASSWORD_LENGTH) {
            if (tryPassword(zipFile, current, outputPath)) {
                System.out.println("✅ Password: " + current);
                passwordFound = true;
            }
            return;
        }

        // Recursive case: try every character in the alphabet
        for (char c : ALPHABET.toCharArray()) {
            crack(current + c, depth + 1, zipFile, outputPath);
        }
    }

    /**
     * Attempts to open the ZIP file using the provided password.
     *
     * @param zipPath    the path to the zip file
     * @param password   the password attempt
     * @param outputPath the directory to extract contents if successful
     * @return true if the password was correct, false otherwise
     */
    private static boolean tryPassword(String zipPath, String password, String outputPath) {
        try {
            ZipFile zip = new ZipFile(zipPath);
            zip.setPassword(password.toCharArray());
            zip.extractAll(outputPath);
            return true; // password correct
        } catch (ZipException e) {
            return false; // wrong password
        }
    }
}
