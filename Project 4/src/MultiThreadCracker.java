import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * MultiThreadCracker
 *
 * Utsav Acharya
 * CS 1181 - Project 4 - Part 2
 *
 * A multithreaded brute-force password cracker for ZIP files.
 * Each thread works on a portion of the search space separately.
 * Targets a password of exactly 5 lowercase letters (a-z).
 */
public class MultiThreadCracker {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz"; // Allowed characters
    private static final int PASSWORD_LENGTH = 5; // Target password length
    private static final int numThreads = 4; // Number of threads
    private static volatile boolean passwordFound = false; // Shared flag to stop when found

    /**
     * Main method to start the multithreaded cracking process.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int threadID = i;
            threads[i] = new Thread(() -> runThread(threadID));
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join(); // Wait for all threads to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " ms");
    }

    /**
     * Executes the work assigned to a specific thread.
     *
     * @param id ID of the current thread (used to divide work)
     */
    private static void runThread(int id) {
        long threadStart = System.currentTimeMillis();
        int range = ALPHABET.length() / numThreads;
        int startIdx = id * range;
        int endIdx = (id == numThreads - 1) ? ALPHABET.length() : startIdx + range;

        String zipCopy = "thread_" + id + ".zip"; // Copy of the ZIP file for this thread
        String extractPath = "out-" + id; // Unique extraction directory for this thread

        try {
            Files.copy(Path.of("protected5.zip"), Path.of(zipCopy));

            for (int i = startIdx; i < endIdx && !passwordFound; i++) {
                char firstChar = ALPHABET.charAt(i);
                recurse(zipCopy, extractPath, new char[PASSWORD_LENGTH], 0, firstChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Files.deleteIfExists(Path.of(zipCopy)); // Delete copied zip
                deleteDir(new File(extractPath)); // Delete extracted files
            } catch (IOException e) {
                e.printStackTrace();
            }
            long threadEnd = System.currentTimeMillis();
            System.out.println("Thread " + id + " ran for " + (threadEnd - threadStart) + " ms");
        }
    }

    /**
     * Recursively builds and tests password guesses.
     *
     * @param zip         ZIP file path for testing
     * @param extractPath Directory to extract contents
     * @param pass        Current password under construction
     * @param idx         Current character position
     * @param first       First character assigned by thread division
     */
    private static void recurse(String zip, String extractPath, char[] pass, int idx, char first) {
        if (passwordFound) return;

        if (idx == 0) {
            pass[idx] = first;
            recurse(zip, extractPath, pass, idx + 1, first);
            return;
        }

        if (idx == PASSWORD_LENGTH) {
            String guess = new String(pass);
            if (tryPassword(zip, extractPath, guess)) {
                System.out.println("✅ Password found: " + guess);
                passwordFound = true;
            }
            return;
        }

        for (char c : ALPHABET.toCharArray()) {
            if (passwordFound) break;
            pass[idx] = c;
            recurse(zip, extractPath, pass, idx + 1, first);
        }
    }

    /**
     * Tries to unlock the zip file using a password guess.
     *
     * @param zipPath    Path to the zip file
     * @param output     Output directory for extracted contents
     * @param guess      Password attempt
     * @return true if successful, false if incorrect
     */
    private static boolean tryPassword(String zipPath, String output, String guess) {
        try {
            ZipFile zipFile = new ZipFile(zipPath);
            zipFile.setPassword(guess.toCharArray());
            zipFile.extractAll(output);
            return true;
        } catch (ZipException e) {
            return false;
        }
    }

    /**
     * Deletes a directory and all files inside it.
     *
     * @param dir directory to delete
     * @throws IOException if an error occurs during deletion
     */
    private static void deleteDir(File dir) throws IOException {
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                deleteDir(file);
            }
        }
        Files.deleteIfExists(dir.toPath());
    }
}
