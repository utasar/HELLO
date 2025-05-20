import javax.swing.*;
import java.awt.*;

public class TicTacToe {
    public static void main(String[] args) {
        //Create JFrame named as MainWindow
        JFrame MainWindow = new JFrame("Button Box");
        MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainWindow.setSize(300, 300);
        MainWindow.setLayout(new BorderLayout());

        //Create a panel for the 3x3 grid for buttons
        JPanel buttonPanel = new JPanel();
        //Create Grid
        buttonPanel.setLayout(new GridLayout(3, 3));

        //Create buttons using a for loop
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            //Adding the button to the grid panel
            buttonPanel.add(button);
        }

        //Create the status label
        JLabel statusLabel = new JLabel("Game status");

        //Add the components to the MainWindow
        MainWindow.add(buttonPanel);
        MainWindow.add(statusLabel, BorderLayout.SOUTH);

        //Make the MainWindow visible
        MainWindow.setVisible(true);
    }
}
