//Utsav Acharya
//LAb 5 part A
import javax.swing.*;
import java.awt.*;

public class SimpleGUI {

    public static void main(String[] args) {
        // Create the main window (JFrame)
        JFrame frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit the program when the window is closed

        // Create a JPanel to hold the button
        JPanel panel = new JPanel();

        // Create the "Convert" button
        JButton convertButton = new JButton("Convert");

        // Add the button to the panel
        panel.add(convertButton);

        // Add the panel to the frame
        frame.add(panel);

        // Set the frame size and make it visible
        frame.setSize(300, 100);  // Set the window size
        frame.setVisible(true);
    }
}
