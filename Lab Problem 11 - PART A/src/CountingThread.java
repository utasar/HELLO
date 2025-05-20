public class CountingThread extends Thread {
    private final String threadName;
    private int sleepTime;

    public CountingThread(String threadName,int sleepTime) {
        this.threadName = threadName;
        this.sleepTime = 10;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(threadName + ": " + i);
            try {
                // Small sleep to increase randomness of output
                Thread.sleep(sleepTime);
            } catch (InterruptedException e)
            {
                System.out.println("Thread interrupted:"+threadName);
                Thread.currentThread().interrupt(); // Restore interrupt status
            }
        }
    }
}