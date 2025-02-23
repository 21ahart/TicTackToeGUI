import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.GridLayout;

public class TicTacToeFrame extends JFrame {
    public static TicTacToeTile[][] board;
    private static String currentPlayer = "X";
    private static int moveCnt = 0;
    private static final int MOVES_FOR_WIN = 5;
    private static final int MOVES_FOR_TIE = 7;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe GUI");
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        board = new TicTacToeTile[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText(" ");
                final int r = row;
                final int c = col;
                board[row][col].addActionListener(e -> clickAction(r, c));
                add(board[row][col]);
            }
        }
    }

    public static void clickAction(int row, int col) {
        if (board[row][col].getText().equals(" ")) {
            board[row][col].setText(currentPlayer);
            moveCnt++;
            int playerNumber = currentPlayer.equals("X") ? 1 : 2;
            if (currentPlayer.equals("X")) {
                board[row][col].setForeground(java.awt.Color.RED);
            } else {
                board[row][col].setForeground(java.awt.Color.BLUE);
            }

            if (moveCnt >= MOVES_FOR_WIN && isWin(currentPlayer)) {
                int response = JOptionPane.showConfirmDialog(null, "Player " + playerNumber + " wins! Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            } else if (moveCnt >= MOVES_FOR_TIE && isTie()) {
                int response = JOptionPane.showConfirmDialog(null, "It's a Tie! Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            } else {
                switchPlayer();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Illegal Move - Please try again.");
        }
    }

    public static void switchPlayer() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
    }

    public static void resetGame() {
        moveCnt = 0;
        currentPlayer = "X";
        clearBoard();
    }

    public static void clearBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setText(" ");
            }
        }
    }

    public static boolean isValidMove(int row, int col) {
        return board[row][col].getText().equals(" ");
    }

    public static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    public static boolean isColWin(String player) {
        for (int col = 0; col < 3; col++) {
            if (board[0][col].getText().equals(player) &&
                board[1][col].getText().equals(player) &&
                board[2][col].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRowWin(String player) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0].getText().equals(player) &&
                board[row][1].getText().equals(player) &&
                board[row][2].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDiagonalWin(String player) {
        if (board[0][0].getText().equals(player) &&
            board[1][1].getText().equals(player) &&
            board[2][2].getText().equals(player)) {
            return true;
        }
        if (board[0][2].getText().equals(player) &&
            board[1][1].getText().equals(player) &&
            board[2][0].getText().equals(player)) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        boolean xFlag = false;
        boolean oFlag = false;
    
        // Check all rows for ties
        for (int row = 0; row < 3; row++) {
            xFlag = oFlag = false;
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().equals("X")) {
                    xFlag = true;
                }
                if (board[row][col].getText().equals("O")) {
                    oFlag = true;
                }
            }
            if (!(xFlag && oFlag)) {
                return false; // No tie if any row does not contain both X and O
            }
        }
    
        // Check all columns for ties
        for (int col = 0; col < 3; col++) {
            xFlag = oFlag = false;
            for (int row = 0; row < 3; row++) {
                if (board[row][col].getText().equals("X")) {
                    xFlag = true;
                }
                if (board[row][col].getText().equals("O")) {
                    oFlag = true;
                }
            }
            if (!(xFlag && oFlag)) {
                return false; // No tie if any column does not contain both X and O
            }
        }
    
        // Check main diagonal for ties
        xFlag = oFlag = false;
        for (int i = 0; i < 3; i++) {
            if (board[i][i].getText().equals("X")) {
                xFlag = true;
            }
            if (board[i][i].getText().equals("O")) {
                oFlag = true;
            }
        }
        if (!(xFlag && oFlag)) {
            return false; // No tie if main diagonal does not contain both X and O
        }
    
        // Check anti-diagonal for ties
        xFlag = oFlag = false;
        for (int i = 0; i < 3; i++) {
            if (board[i][2 - i].getText().equals("X")) {
                xFlag = true;
            }
            if (board[i][2 - i].getText().equals("O")) {
                oFlag = true;
            }
        }
        if (!(xFlag && oFlag)) {
            return false; // No tie if anti-diagonal does not contain both X and O
        }
    
        // If all rows, columns, and diagonals contain both X and O, it's a tie
        return true;
    }
}
