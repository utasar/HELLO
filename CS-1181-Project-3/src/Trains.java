/**
 * Represents a train event in the simulation, including its arrival and departure times.
 * Extends Events to be processed in the event queue.
 *
 * A train initially starts as blocking the crossing. Once processed,
 * it toggles to not blocking and eventually leaves the crossing.
 *
 * Author: Utsav
 * @version 1.0

 */
public class Trains extends Events {
    private boolean blocking = true;

    /**
     * Constructs a train event at the specified time.
     *
     * @param time the time at which the train event occurs
     */
    public Trains(double time) {
        this.time = time;
    }

    /**
     * Checks if the train is currently blocking the crossing.
     *
     * @return true if blocking, false otherwise
     */
    public boolean isBlocking() {
        return blocking;
    }

    /**
     * Marks the train as no longer blocking the crossing.
     */
    public void setNotBlocking() {
        this.blocking = false;
    }
}
