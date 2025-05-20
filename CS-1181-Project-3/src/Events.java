/**
 * Abstract base class representing a scheduled event in the simulation.
 * Used for both train and truck events in the simulation queue.
 * Implements Comparable for priority queue sorting based on time.
 *
 * @author Utsav
 * @version 1.0

 */
public abstract class Events implements Comparable<Events> {
    protected double time;

    /**
     * Gets the event time.
     *
     * @return the time the event occurs
     */
    public double getTime() {
        return time;
    }

    /**
     * Sets the event time.
     *
     * @param time the time to set
     */
    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public int compareTo(Events other) {
        return Double.compare(this.time, other.time);
    }
}
