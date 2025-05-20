//Utsav Acharya
//Lab Problem 7 - PART B
//03/20/2025
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main class to test the ComponentList class.
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab-7");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Create ComponentList with initial components
        ComponentList<JLabel> colorList = new ComponentList<>(new ArrayList<>(Arrays.asList(
                new JLabel("Red"),
                new JLabel("Blue"),
                new JLabel("Green"),
                new JLabel("Yellow")
        )));

        // Set font and alignment for all labels (same bold font for consistency)
        Font boldFont = new Font("Arial", Font.BOLD, 14);
        for (Component c : colorList.getComponents()) {
            if (c instanceof JLabel) {
                ((JLabel) c).setFont(boldFont);
                ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);
            }
        }

        // Add a new component and replace the first one
        colorList.add(new JLabel("Orange"));
        colorList.setComponentAtIndex(0, new JLabel("Maroon"));

        // Ensure all labels have the same font and alignment
        for (Component c : colorList.getComponents()) {
            if (c instanceof JLabel) {
                ((JLabel) c).setFont(boldFont);
                ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);
            }
        }

        // Add to frame at the top center
        frame.add(colorList, BorderLayout.NORTH);

        // Display the frame
        frame.setVisible(true);
    }
}
