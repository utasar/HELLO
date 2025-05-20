/**
 * This class is responsible for checking prime numbers within a specific range.
 * It extends the Thread class so each object can run concurrently with others.
 * Designed for use in a multithreaded prime-counting program.
 *
 * Author: Utsav Acharya
 * Lab: 11 - Part B
 */
public class PrimeThread extends Thread {
    private int start;
    private int end;
    private int primeCount = 0;

    /**
     * Constructor to initialize the range for this thread.
     *
     * @param start starting number (inclusive)
     * @param end   ending number (exclusive)
     */
    public PrimeThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Method that checks if a given number is prime.
     *
     * @param num the number to check
     * @return true if num is prime, false otherwise
     */
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;

        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }

        return true;
    }

    /**
     * The main logic that runs when the thread starts.
     * It goes through the assigned range and counts primes.
     */
    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            if (isPrime(i)) {
                primeCount++;
            }
        }
    }

    /**
     * @return total number of prime numbers found in this thread's range
     */
    public int getPrimeCount() {
        return primeCount;
    }
}
