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
                int response = JOptionPane.showConfirmDialog(null, "Player " + currentPlayer + " wins! Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
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

    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so 
        // no win is possible
        // Check for row ties
        for(int row=0; row < 3; row++)
        {
            if(board[row][0].equals("X") || 
               board[row][1].equals("X") ||
               board[row][2].equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].equals("O") || 
               board[row][1].equals("O") ||
               board[row][2].equals("O"))
            {
                oFlag = true; // there is an O in this row
            }
            
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }
            
            xFlag = oFlag = false;
            
        }
        // Now scan the columns
        for(int col=0; col < 3; col++)
        {
            if(board[0][col].equals("X") || 
               board[1][col].equals("X") ||
               board[2][col].equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].equals("O") || 
               board[1][col].equals("O") ||
               board[2][col].equals("O"))
            {
                oFlag = true; // there is an O in this col
            }
            
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }    
        // Now check for the diagonals
        xFlag = oFlag = false;
        
        if(board[0][0].equals("X") ||
           board[1][1].equals("X") ||    
           board[2][2].equals("X") )
        {
            xFlag = true;
        } 
        if(board[0][0].equals("O") ||
           board[1][1].equals("O") ||    
           board[2][2].equals("O") )
        {
            oFlag = true;
        } 
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }        
        xFlag = oFlag = false;        
        
        if(board[0][2].equals("X") ||
           board[1][1].equals("X") ||    
           board[2][0].equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].equals("O") ||
           board[1][1].equals("O") ||    
           board[2][0].equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }        

        // Checked every vector so I know I have a tie
        return true;
    }
}
