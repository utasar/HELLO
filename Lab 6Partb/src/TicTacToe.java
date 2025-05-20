/**
 * Tic-Tac-Toe Game
 * CS 1181 - Programming Project 2
 * Purpose: Develop a GUI-based Tic-Tac-Toe game using Java Swing.
 * This project demonstrates object-oriented programming concepts,
 * event handling, and user interaction.
 *
 * Features:
 * - Game window using JFrame
 * - Dynamic grid size
 * - Turn-based multiplayer game
 * - Scoring and win detection
 * - Game instructions
 *
 * Author: Utsav Acharya
 * Date: 03/01/2025
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TicTacToeGame class sets up the game window and initializes components.
 */
public class TicTacToe {

    private static final int GRID_SIZE = 3; // Default grid size
    private static JFrame gameFrame;
    private static JPanel gridPanel;
    private static JLabel statusLabel;
    private static JButton[][] tiles;
    private static GameEngine game;

    /**
     * Main method to initialize the game window.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        setupGame(GRID_SIZE);
    }

    /**
     * Sets up the game window and components.
     * @param gridSize Size of the game grid
     */
    private static void setupGame(int gridSize) {
        if (gameFrame != null) {
            gameFrame.dispose();
        }

        gameFrame = new JFrame("Tic-Tac-Toe");
        gameFrame.setSize(400, 400);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());

        statusLabel = new JLabel("Player 1's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gameFrame.add(statusLabel, BorderLayout.NORTH);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gridSize, gridSize));

        tiles = new JButton[gridSize][gridSize];
        game = new GameEngine(statusLabel, tiles);

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                tiles[row][col] = new JButton();
                tiles[row][col].setFont(new Font("Arial", Font.BOLD, 40));
                tiles[row][col].setFocusPainted(false);
                tiles[row][col].setToolTipText("Click to make a move");
                tiles[row][col].addActionListener(game);
                gridPanel.add(tiles[row][col]);
            }
        }

        gameFrame.add(gridPanel, BorderLayout.CENTER);

        setupMenu();

        gameFrame.setVisible(true);
    }

    /**
     * Sets up the game menu with options.
     */
    private static void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");

        JMenuItem restartGame = new JMenuItem("Restart Game");
        JMenuItem instructions = new JMenuItem("Instructions");
        JMenuItem exitGame = new JMenuItem("Exit");

        restartGame.addActionListener(e -> setupGame(GRID_SIZE));
        instructions.addActionListener(e -> showInstructions());
        exitGame.addActionListener(e -> System.exit(0));

        optionsMenu.add(restartGame);
        optionsMenu.add(instructions);
        optionsMenu.add(exitGame);

        menuBar.add(optionsMenu);
        gameFrame.setJMenuBar(menuBar);
    }

    /**
     * Displays game instructions.
     */
    private static void showInstructions() {
        JOptionPane.showMessageDialog(gameFrame,
                "Welcome to Tic-Tac-Toe!\n" +
                        "1. Players take turns clicking tiles.\n" +
                        "2. First player is X, second player is O.\n" +
                        "3. The goal is to get 3 in a row (horizontal, vertical, or diagonal).\n" +
                        "4. The first player to match 3 wins.\n" +
                        "5. If all tiles are filled and no one wins, it's a draw.",
                "Game Instructions", JOptionPane.INFORMATION_MESSAGE);
    }
}
