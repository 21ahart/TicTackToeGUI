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

            if (moveCnt >= MOVES_FOR_WIN && isWin(currentPlayer)) {
                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                resetGame();
            } else if (moveCnt >= MOVES_FOR_TIE && isTie()) {
                JOptionPane.showMessageDialog(null, "It's a Tie!");
                resetGame();
            } else {
                switchPlayer();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Illegal Move");
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

    public static boolean isTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}