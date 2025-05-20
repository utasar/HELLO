import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Maingame class to for implementing action listner for reading button clicks
 * It also sends basic response for the button click on the 3x3 game board
 */
public class MainGame implements ActionListener {
    private boolean isPlayerOneTurn = true; //First player to start
    private JLabel statusLabel; //Game Status will display turn of player

    // constructor taking statuslabel arguement
    public MainGame(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    // For handling the event of buttons click
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource(); //Find which button was clicked

        if (buttonClicked.getText().equals("X") || buttonClicked.getText().equals("O")) {
            return;
        }

        //turns of the players changes the button label
        if (isPlayerOneTurn) {
            buttonClicked.setText("X");
            statusLabel.setText("Player 2's turn");
        } else {
            buttonClicked.setText("O");
            statusLabel.setText("Player 1's turn");
        }

        //Changing the turn
        isPlayerOneTurn = !isPlayerOneTurn;

    }
}
