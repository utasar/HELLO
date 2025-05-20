public class Main {
    public static void main(String[] args) {
        // Creating two CountingThread
        CountingThread thread1 = new CountingThread("Thread 1",10);// initialize thread 1 with sleep time 10
        CountingThread thread2 = new CountingThread("Thread 2",10);// initilize thread 2 with sleep time 10

        // Start both threads same time
        thread1.start();
        thread2.start();


        try {
            thread1.join();// wait for thread 1 to finish
            thread2.join();// wait for thread 2 to finish
        } catch (InterruptedException e) {
            // System.out.println("Main thread interrupted.");
            e.printStackTrace();
        }
        System.out.println("Main Done");
    }
}
