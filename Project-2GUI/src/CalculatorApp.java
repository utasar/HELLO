/**
 * Simple Calculator Program
 *
 * This program implements a basic calculator using Java Swing.
 * It supports basic arithmetic operations (addition, subtraction, multiplication, division),
 * percentage calculation, sign inversion, and square root calculation.
 *
 * Author: Utsav Acharya
 * Date: 03/15/2025
 */
// i have done this before using javascript by the help of coding with harry (during  my high school)
// this time i just revise while edit something see something with neso accademy and bro code youtube and i just work on it
// chatgpt is use for commenting ,some concept for buttons
//childhood friend arpan help me and suggestion on coloring and some switch case simplification
//to make sure i just want add all online resource but mainly youtube my old code and some changes
//https://github.com/utasar/University
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Calculator class handles the UI and logic for a basic calculator.
 */
public class CalculatorApp {

    private static final int WIDTH = 360;
    private static final int HEIGHT = 540;

    private static final Color LIGHT_GRAY = new Color(212, 212, 210, 249);
    private static final Color DARK_GRAY = new Color(80, 80, 80, 229);
    private static final Color BLACK = new Color(28, 28, 28);
    private static final Color ORANGE = new Color(255, 149, 0, 245);

    private String currentValue = "0";
    private String operator = null;
    private String previousValue = null;

    private JFrame frame;
    private JLabel displayLabel;
    private JPanel buttonPanel;

    private static final String[] BUTTON_LABELS = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };

    /**
     * Constructor sets up the calculator UI.
     */
    public CalculatorApp() {
        frame = new JFrame("Calculator");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        setupDisplay();
        setupButtons();

        frame.setVisible(true);
    }

    /**
     * Sets up the display panel.
     */
    private void setupDisplay() {
        displayLabel = new JLabel("0", SwingConstants.RIGHT);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setBackground(BLACK);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setOpaque(true);

        frame.add(displayLabel, BorderLayout.NORTH);
    }

    /**
     * Sets up the button panel and adds action listeners.
     */
    private void setupButtons() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        for (String label : BUTTON_LABELS) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setFocusable(false);
            button.setBorder(new LineBorder(BLACK));

            if ("AC±%".contains(label)) {
                button.setBackground(LIGHT_GRAY);
                button.setForeground(BLACK);
            } else if ("÷×-+=".contains(label)) {
                button.setBackground(ORANGE);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(DARK_GRAY);
                button.setForeground(Color.WHITE);
            }

            button.addActionListener(e -> handleInput(label));
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * Handles button input and calculation logic.
     * @param input The button label pressed.
     */
    private void handleInput(String input) {
        if ("0123456789".contains(input)) {
            handleNumberInput(input);
        } else if ("+−×÷".contains(input)) {
            handleOperatorInput(input);
        } else if (input.equals("=")) {
            calculateResult();
        } else if (input.equals("AC")) {
            reset();
        } else if (input.equals("+/-")) {
            invertSign();
        } else if (input.equals("%")) {
            calculatePercentage();
        } else if (input.equals("√")) {
            calculateSquareRoot();
        }
    }

    /**
     * Handles number input.
     */
    private void handleNumberInput(String num) {
        if (currentValue.equals("0")) {
            currentValue = num;
        } else {
            currentValue += num;
        }
        updateDisplay();
    }

    /**
     * Handles operator input.
     */
    private void handleOperatorInput(String op) {
        if (previousValue == null) {
            previousValue = currentValue;
            currentValue = "0";
            operator = op;
        }
    }

    /**
     * Handles calculation logic.
     */
    private void calculateResult() {
        if (previousValue == null || operator == null) return;

        double a = Double.parseDouble(previousValue);
        double b = Double.parseDouble(currentValue);
        double result = 0;

        switch (operator) {
            case "+": result = a + b; break;
            case "−": result = a - b; break;
            case "×": result = a * b; break;
            case "÷": result = a / b; break;
        }

        currentValue = String.valueOf(result);
        operator = null;
        previousValue = null;
        updateDisplay();
    }

    /**
     * Inverts the sign of the current value.
     */
    private void invertSign() {
        double value = Double.parseDouble(currentValue) * -1;
        currentValue = String.valueOf(value);
        updateDisplay();
    }

    /**
     * Converts current value to a percentage.
     */
    private void calculatePercentage() {
        double value = Double.parseDouble(currentValue) / 100;
        currentValue = String.valueOf(value);
        updateDisplay();
    }

    /**
     * Calculates the square root of the current value.
     */
    private void calculateSquareRoot() {
        double value = Math.sqrt(Double.parseDouble(currentValue));
        currentValue = String.valueOf(value);
        updateDisplay();
    }

    /**
     * Resets the calculator to its initial state.
     */
    private void reset() {
        currentValue = "0";
        operator = null;
        previousValue = null;
        updateDisplay();
    }

    /**
     * Updates the display label.
     */
    private void updateDisplay() {
        displayLabel.setText(currentValue);
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}
