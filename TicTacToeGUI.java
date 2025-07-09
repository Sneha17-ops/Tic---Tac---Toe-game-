import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    JButton[][] buttons = new JButton[3][3];
    JLabel statusLabel;
    boolean xTurn = true;

    public TicTacToeGUI() {
        setTitle("🎮 Tic-Tac-Toe Game");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layouts
        setLayout(new BorderLayout());

        // Status Label
        statusLabel = new JLabel("Player X's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(Color.BLUE);
        add(statusLabel, BorderLayout.NORTH);

        // Grid Panel
        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 60);

        // Initialize Buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setBackground(Color.WHITE);
                gridPanel.add(buttons[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        // Play Again Button
        JButton resetButton = new JButton("🔁 Play Again");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 18));
        resetButton.setBackground(new Color(200, 255, 200));
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return; // Already clicked
        }

        if (xTurn) {
            clicked.setText("X");
            clicked.setForeground(Color.RED);
            statusLabel.setText("Player O's Turn");
        } else {
            clicked.setText("O");
            clicked.setForeground(Color.MAGENTA);
            statusLabel.setText("Player X's Turn");
        }

        xTurn = !xTurn;
        checkWinner();
    }

    private void checkWinner() {
        String winner = null;

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") &&
                buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText())) {
                winner = buttons[i][0].getText();
            }

            if (!buttons[0][i].getText().equals("") &&
                buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText())) {
                winner = buttons[0][i].getText();
            }
        }

        // Check diagonals
        if (!buttons[0][0].getText().equals("") &&
            buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText())) {
            winner = buttons[0][0].getText();
        }

        if (!buttons[0][2].getText().equals("") &&
            buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText())) {
            winner = buttons[0][2].getText();
        }

        // If winner found
        if (winner != null) {
            JOptionPane.showMessageDialog(this, "🎉 Player " + winner + " wins!");
            disableButtons();
            statusLabel.setText("Game Over");
        } else if (isDraw()) {
            JOptionPane.showMessageDialog(this, "🤝 It's a Draw!");
            statusLabel.setText("Game Over");
        }
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals(""))
                    return false;
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setEnabled(false);
    }

    private void resetGame() {
        xTurn = true;
        statusLabel.setText("Player X's Turn");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(Color.WHITE);
            }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}
 