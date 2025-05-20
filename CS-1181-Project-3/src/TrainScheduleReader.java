import java.io.*;
import java.util.*;

/**
 * Utility class to read a train schedule from a file and convert it into a list of train events.
 * Each line in the file should contain a number representing the train arrival time in minutes.
 *
 * Author: Utsav
 * @version 1.0

 */
public class TrainScheduleReader {

    /**
     * Reads a train schedule file and returns a list of Trains objects.
     *
     * @param filename the name of the schedule file (e.g., "train_schedule.txt")
     * @return list of Trains events
     */
    public static List<Trains> readSchedule(String filename) {
        List<Trains> trains = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Train Schedule");
            while ((line = reader.readLine()) != null) {
                try {
                    double time = Double.parseDouble(line.trim());
                    trains.add(new Trains(time));
                } catch (NumberFormatException e) {
                    System.err.println( line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading train schedule: " + e.getMessage());
        }
        return trains;
    }
}
