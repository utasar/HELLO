import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class manages the whole process of counting primes using multiple threads.
 * The user is prompted to input the number of threads and the upper bound for checking primes.
 * Then, each thread processes a chunk of the range.
 *
 * Author: Utsav Acharya
 * Lab: 11 - Part B
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user how many threads and the max value to check
        System.out.println("Enter number of threads and the upper limit (n): ");
        int numThreads = scanner.nextInt();
        int n = scanner.nextInt();

        // Record the starting time
        long startTime = System.currentTimeMillis();

        // Split range evenly across threads
        int rangeSize = n / numThreads;
        ArrayList<PrimeThread> threads = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            int start = i * rangeSize + 1;
            int end = (i == numThreads - 1) ? n + 1 : start + rangeSize;
            PrimeThread thread = new PrimeThread(start, end);
            threads.add(thread);
            thread.start();  // Start the thread
        }

        // Wait for all threads to finish and gather results
        int totalPrimes = 0;
        for (PrimeThread thread : threads) {
            try {
                thread.join();  // Make sure this thread is finished
                totalPrimes += thread.getPrimeCount();  // Add this thread's prime count
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }

        // Record end time and calculate elapsed time
        long endTime = System.currentTimeMillis();

        // Output the results
        System.out.println("Total prime numbers found: " + totalPrimes);
        System.out.println("Threads used: " + numThreads);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");

        scanner.close();
    }
}
