/**
 * Represents a truck in the package delivery simulation.
 * Each truck goes through several states as it moves through its delivery route.
 *
 * Author: Utsav
 * @version 1.0

 */
public class Trucks extends Events {

    /**
     * Enum representing the state of a truck during its journey.
     */
    public enum TruckState {
        BEGIN, AT_CROSSING, CROSSING, DONE
    }

    private int id;
    private TruckState state;
    private double startTime;

    /**
     * Constructs a Truck object with an ID and initial event time.
     *
     * @param id the truck identifier
     * @param time the starting time of the truck
     */
    public Trucks(int id, double time) {
        this.id = id;
        this.time = time;
        this.state = TruckState.BEGIN;
        this.startTime = time;
    }

    /**
     * Gets the truck's ID.
     *
     * @return truck ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the current state of the truck.
     *
     * @return current TruckState
     */
    public TruckState getState() {
        return state;
    }

    /**
     * Advances the truck to its next state.
     */
    public void advanceState() {
        switch (state) {
            case BEGIN -> state = TruckState.AT_CROSSING;
            case AT_CROSSING -> state = TruckState.CROSSING;
            case CROSSING -> state = TruckState.DONE;
            default -> {
            }
        }
    }

    /**
     * Gets the starting time of the truck's journey.
     *
     * @return the time the truck started its trip
     */
    public double getStartTime() {
        return startTime;
    }
}
