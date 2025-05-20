import java.util.*;

/**
 * Manages the simulation of package deliveries using drones and trucks,
 * considering train schedules that may affect delivery times.
 *
 * This class initializes constants related to the simulation, calculates
 * the number of drones and trucks needed, and processes events such as
 * train arrivals and departures, and truck movements.
 *
 * @author Utsav Acharya
 * @version 1.0
 *
 */
public class PackageDelivery {

    /**
     * Runs the package delivery simulation.
     * Initializes constants, calculates the number of drones and trucks,
     * processes train schedules, and manages the event queue for deliveries.
     */
    public static void runSimulation() {
        // Constants
        final int TOTAL_PACKAGES = 1500;
        final int DIST_TO_CROSSING = 3000;        // Distance to crossing in meters
        final int DIST_AFTER_CROSSING = 27000;    // Distance after crossing in meters
        final int DRONE_SPEED = 500;              // Drone speed in meters per minute
        final int TRUCK_SPEED = 30;               // Truck speed in meters per minute
        final int TRUCK_CAPACITY = 10;            // Number of packages a truck can carry
        final int DRONE_START_DELAY = 3;          // Delay between drone launches in minutes
        final int TRUCK_START_DELAY = 15;         // Delay between truck departures in minutes
        final float PERCENT_BY_DRONE = 0.47f;     // Percentage of deliveries by drone

        // Calculate the number of drones and trucks needed
        int totalDrones = (int) (TOTAL_PACKAGES * PERCENT_BY_DRONE);
        int leftoverPackages = TOTAL_PACKAGES - totalDrones;
        int totalTrucks = (leftoverPackages + TRUCK_CAPACITY - 1) / TRUCK_CAPACITY; // Ceiling division

        // Calculate drone trip times
        double droneTripTime = (DIST_TO_CROSSING + DIST_AFTER_CROSSING) / (double) DRONE_SPEED;
        double totalDroneTime = (totalDrones - 1) * DRONE_START_DELAY + droneTripTime;

        // Initialize event queue and truck lists
        PriorityQueue<Events> eventQueue = new PriorityQueue<>();
        ArrayList<Trucks> allTrucks = new ArrayList<>();
        ArrayList<Trucks> waitingTrucks = new ArrayList<>();

        // Read train schedule and add train events to the event queue
        List<Trains> trainSchedule = TrainScheduleReader.readSchedule("src\\train_schedule.txt");
        for (Trains train : trainSchedule) {
            eventQueue.offer(train);
        }

        // Load trucks into the simulation and add truck events to the event queue
        for (int i = 0; i < totalTrucks; i++) {
            Trucks truck = new Trucks(i, i * TRUCK_START_DELAY);
            eventQueue.offer(truck);
            allTrucks.add(truck);
        }

        // Simulation loop
        double simTime = 0;
        boolean trainBlocking = false;

        while (!eventQueue.isEmpty()) {
            Events event = eventQueue.poll();
            simTime = event.getTime();

            if (event instanceof Trains) {
                Trains train = (Trains) event;
                if (train.isBlocking()) {
                    System.out.println(simTime + ": Train arrives at crossing");
                    train.setNotBlocking();
                    eventQueue.offer(train);
                    trainBlocking = true;
                } else {
                    System.out.println(simTime + ": Train leaves crossing");
                    for (Trucks truck : waitingTrucks) {
                        truck.setTime(simTime + 1);
                        truck.advanceState();
                        eventQueue.offer(truck);
                    }
                    waitingTrucks.clear();
                    trainBlocking = false;
                }
            } else if (event instanceof Trucks) {
                Trucks truck = (Trucks) event;
                switch (truck.getState()) {
                    case BEGIN:
                        System.out.println(simTime + ": Truck #" + truck.getId() + " starts journey");
                        truck.setTime(simTime + (DIST_TO_CROSSING / (double) TRUCK_SPEED));
                        truck.advanceState();
                        eventQueue.offer(truck);
                        break;
                    case AT_CROSSING:
                        System.out.println(simTime + ": Truck #" + truck.getId() + " waits at crossing");
                        if (trainBlocking) {
                            waitingTrucks.add(truck);
                        } else {
                            truck.setTime(simTime + 1);
                            truck.advanceState();
                            eventQueue.offer(truck);
                        }
                        break;
                    case CROSSING:
                        System.out.println(simTime + ": Truck #" + truck.getId() + " crosses");
                        truck.setTime(simTime + (DIST_AFTER_CROSSING / (double) TRUCK_SPEED));
                        truck.advanceState();
                        eventQueue.offer(truck);
                        break;
                    case DONE:
                        System.out.println(simTime + ": Truck #" + truck.getId() + " completed delivery");
                        break;
                }
            }
        }

        // Final statistics
        System.out.println("\n---- STATS ----");
        double totalTruckTime = 0;
        for (Trucks truck : allTrucks) {
            double tripTime = truck.getTime() - truck.getStartTime();
            System.out.printf("Truck #%d total time: %.2f minutes%n", truck.getId(), tripTime);
            totalTruckTime += tripTime;
        }

        System.out.printf("Drone trip time (one trip): %.2f minutes%n", droneTripTime);
        System.out.printf("Total time for all drones: %.2f minutes%n", totalDroneTime);
        System.out.printf("Average truck delivery time: %.2f minutes%n", totalTruckTime / totalTrucks);
    }
}
