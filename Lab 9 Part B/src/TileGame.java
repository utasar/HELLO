//Utsav Acharya
//Lab 9 Part B
// 04/03/2025
import java.util.ArrayDeque;
import java.util.Queue;

public class TileGame {
    public static int tileGame(ArrayDeque<Integer> stack, Queue<Integer> q) {
        int turns = 0;

        while (!stack.isEmpty()) {
            turns++; // Increment turn count
            int topTile = stack.pop(); //Removes whatever is in the top of the stack tile for both 1,2,3 conditions

            if (topTile == 1) {
                // Remove next one from the stack
                if (!stack.isEmpty()) {
                    stack.pop(); // Remove second tile
                }
            } else if (topTile == 2) {
                // Remove two more from the stack
                if (!stack.isEmpty()) {
                    stack.pop(); // Remove second tile
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // Remove third tile
                }
            } else if (topTile == 3) {
                // Add three tiles from the queue to the stack
                for (int i = 0; i < 3; i++) {
                    if (!q.isEmpty()) {
                        stack.push(q.poll()); // Take from the queue and add to the stack
                    }
                }
            }
        }

        return turns; // Return the total number of turns taken
    }

    public static void main(String[] args) {
        // Example usage of the TileGame
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Queue<Integer> queue = new ArrayDeque<>();

        // Initializing the stack and queue as per the problem statement
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(2);

        // Queue contains the items
        queue.offer(1);
        queue.offer(2);
        queue.offer(2);
        queue.offer(1);
        queue.offer(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        // Execute the tile game and print the result
        int turns = tileGame(stack, queue);
        System.out.println("Number of turns taken: " + turns);
    }
}
