/**
 * The Drone class represents a package delivery drone that operates
 * when no trains are blocking the crossing. It waits for idle time
 * and starts delivery for a fixed period.
 *
 * Author: Utsav
 * Version: 1.0
 * Date: 2025-04-06
 */
public class Drone extends Events {

    private boolean active = false;

    /**
     * Constructs a Drone event at the given time.
     *
     * @param time The time the drone becomes active.
     */
    public Drone(double time) {
        this.time = time;
    }

    /**
     * Checks if the drone is currently active.
     *
     * @return true if active, false otherwise.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the drone's active status.
     *
     * @param active true to activate the drone, false to deactivate.
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}
