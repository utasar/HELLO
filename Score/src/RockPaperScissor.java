import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RockPaperScissor {
    private enum Choice {
        ROCK, PAPER, SCISSORS
    }

    private JLabel resultLabel;
    private JLabel scoreLabel;
    private int playerScore = 0;
    private int computerScore = 0;

    public static void main(String[] args) {
        // Run GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new RockPaperScissor().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        // Create frame
        JFrame frame = new JFrame("Rock Paper Scissors");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create title
        JLabel titleLabel = new JLabel("Rock Paper Scissors", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Create buttons panel
        JPanel buttonsPanel = new JPanel();
        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");

        rockButton.addActionListener(e -> playGame(Choice.ROCK));
        paperButton.addActionListener(e -> playGame(Choice.PAPER));
        scissorsButton.addActionListener(e -> playGame(Choice.SCISSORS));

        buttonsPanel.add(rockButton);
        buttonsPanel.add(paperButton);
        buttonsPanel.add(scissorsButton);
        frame.add(buttonsPanel, BorderLayout.CENTER);

        // Create results panel
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        resultLabel = new JLabel("Make your choice!", SwingConstants.CENTER);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel("Player: 0 - Computer: 0", SwingConstants.CENTER);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultsPanel.add(Box.createVerticalStrut(10));
        resultsPanel.add(resultLabel);
        resultsPanel.add(Box.createVerticalStrut(10));
        resultsPanel.add(scoreLabel);
        resultsPanel.add(Box.createVerticalStrut(10));

        frame.add(resultsPanel, BorderLayout.SOUTH);

        // Display the window
        frame.setVisible(true);
    }

    private void playGame(Choice playerChoice) {
        // Computer makes a random choice
        Choice computerChoice = getRandomChoice();

        // Determine winner
        String result;
        if (playerChoice == computerChoice) {
            result = "It's a tie! Both chose " + playerChoice;
        } else if ((playerChoice == Choice.ROCK && computerChoice == Choice.SCISSORS) ||
                (playerChoice == Choice.PAPER && computerChoice == Choice.ROCK) ||
                (playerChoice == Choice.SCISSORS && computerChoice == Choice.PAPER)) {
            result = "You win! " + playerChoice + " beats " + computerChoice;
            playerScore++;
        } else {
            result = "Computer wins! " + computerChoice + " beats " + playerChoice;
            computerScore++;
        }

        // Update UI
        resultLabel.setText(result);
        scoreLabel.setText("Player: " + playerScore + " - Computer: " + computerScore);
    }

    private Choice getRandomChoice() {
        Random random = new Random();
        Choice[] choices = Choice.values();
        return choices[random.nextInt(choices.length)];
    }
}