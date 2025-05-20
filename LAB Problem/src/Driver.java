import java.util.*;

public class Driver {
    public static void main(String[] args) {
        // Use Part A's golfers here (replace with Part A data)
        Golfer golfer1 = new Golfer("Jay", "Smith", -13, 17);
        Golfer golfer2 = new Golfer("DeShaun", "Smith", -18, 16);
        Golfer golfer3 = new Golfer("DeShaun", "Taylor", -11, 2);

        // Create an ArrayList and add the golfers to it
        ArrayList<Golfer> golfers = new ArrayList<>();
        golfers.add(golfer1);
        golfers.add(golfer2);
        golfers.add(golfer3);

        // Print out the ArrayList before sorting
        System.out.println("Before sorting: " + golfers);

        // Sort the ArrayList
        Collections.sort(golfers);

        // Print out the ArrayList after sorting
        System.out.println("After sorting: " + golfers);
    }
}
