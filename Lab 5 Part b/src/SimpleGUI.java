//Utsav Acharya
//LAb 5 part B
import javax.swing.*;
import java.awt.*;

public class SimpleGUI {


    /**
     *
     * Main method to display Java Frame GUI application
     */
    public static void main(String[] args) {
        //Creating Instance of JFrame
        TemperatureConverterFrame frame = new TemperatureConverterFrame();

        // frame.setSize(400,400); //Setting Size no needed so commented
        frame.pack(); // setSize replaced with pack so that it will autosize
        frame.setLocation(100,100);//setting location
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close Operation
        frame.setVisible(true); //Visibility
    }
}
