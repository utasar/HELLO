//Utsav Acharya
//LAb-10-part-A
public class Main {

    public static void main(String[] args) {
        System.out.println("Counting down from 10 to 3:");
        countDown(10, 3);
        System.out.println("\nCounting down from 4 to 5:");
        countDown(4, 5);
        System.out.println("\nCounting down from -2 to -6:");
        countDown(-2, -6);
    }

    public static void countDown(int start, int stop) {
        // Base case: if stop is greater than or equal to start, print the input parameters and exit.
        if (stop > start) {
            return;
        }
        // print the current value, decrease the counter and recurse
        System.out.print(start + " ");
        countDown(start - 1, stop);
    }
}
