import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TemperatureConverterFrame inherits JFrame to create GUI
 */
public class TemperatureConverterFrame extends JFrame {
    /**
     * Constructor for frame and its components
     */
    public TemperatureConverterFrame() {

        // To Create a JPanel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

        // Creating Label for required Text field and its label for Farenheite and celcius
        JLabel fahrenheitLabel = new JLabel("Fahrenheit: ");
        JTextField fahrenheitTextField = new JTextField(10); // Set the text field width

        JLabel celsiusLabel = new JLabel("Celsius: ");
        JTextField celsiusTextField = new JTextField(10);
        //celsiusTextField.setEditable(false); // Make it read-only

        // Add the labels and text fields to the input panel (same line)
        inputPanel.add(fahrenheitLabel);
        inputPanel.add(fahrenheitTextField);
        inputPanel.add(celsiusLabel);
        inputPanel.add(celsiusTextField);

        // Create a JPanel for the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
        JButton convertButton = new JButton("Convert");

        //Adding ActionListner to the button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Assign the farenheit value entered in text field 
                    String fahrenheitText = fahrenheitTextField.getText();
                    //Call ConverterMethod from TemperatureConverter Class
                    double celsius = TemperatureConverter.fahrenheitToCelsius(fahrenheitText);
                    // Display the Celsius value in the output field
                    celsiusTextField.setText(String.format("%.2f", celsius));
                } catch (IllegalArgumentException ex) {
                    //Catches the exception for wrong entry like space or other characters
                    JOptionPane.showMessageDialog(TemperatureConverterFrame.this, ex.getMessage(), "Conversion Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //Adding Button
        buttonPanel.add(convertButton);
        // Add both Button Pannel and text panal to the Frame
        add(inputPanel, BorderLayout.CENTER);// Align center
        add(buttonPanel, BorderLayout.SOUTH); // adding buttonPannel to align with upper pannnel
    }
}

