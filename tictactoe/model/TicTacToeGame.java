package ss.tictactoe.model;



import ss.tictactoe.ui.HumanPlayer;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeGame implements Game {

    private Board board = new Board();
    private final AbstractPlayer player1;
    private final AbstractPlayer player2;
    private AbstractPlayer turn;

    public TicTacToeGame(AbstractPlayer player1, AbstractPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = player1;
    }

    /**
     * Check if the game is over, i.e., there is a winner or no more moves are available.
     *
     * @return whether the game is over
     */
    @Override
    public boolean isGameover() {
        return board.isFull() || board.hasWinner();
    }

    /**
     * Query whose turn it is
     *
     * @return the player whose turn it is
     */
    @Override
    public AbstractPlayer getTurn() {
        return turn;
    }

    /**
     * Get the winner of the game. If the game is a draw, then this method returns null.
     *
     * @return the winner, or null if no player is the winner
     */
    @Override
    public AbstractPlayer getWinner() {
        if (board.hasWinner()) {
            if (turn == player1) {
                return player2;
            } else {
                return player1;
            }
        }
        return null;
    }

    /**
     * Return all moves that are valid in the current state of the game
     *
     * @return the list of currently valid moves
     */
    @Override
    public List<Move> getValidMoves() {
        List<Move> moves = new ArrayList<>();
        if (!isGameover()) {
            for (int i = 0; i < Board.DIM * Board.DIM; i++) {
                if (board.isEmptyField(i)) {
                    moves.add(new TicTacToeMove(i/Board.DIM, i%Board.DIM));
                }
            }
        }
        return moves;
    }

    /**
     * Check if a move is a valid move
     *
     * @param move the move to check
     * @return true if the move is a valid move
     */
    @Override
    public boolean isValidMove(Move move) {
        boolean valid = false;
        if (move instanceof TicTacToeMove) {
            TicTacToeMove tttMove = (TicTacToeMove) move;
            valid = board.isEmptyField(tttMove.getRow(), tttMove.getCol());
        }
        return valid;
    }

    /**
     * Perform the move, assuming it is a valid move.
     *
     * @param move the move to play
     */
    @Override
    public void doMove(Move move) {
        if (isValidMove(move)) {
            if (turn == player1) {
                board.setField(((TicTacToeMove) move).getRow(), ((TicTacToeMove) move).getCol(), Mark.XX);
                turn = player2;
            } else {
                board.setField(((TicTacToeMove) move).getRow(), ((TicTacToeMove) move).getCol(), Mark.OO);
                turn = player1;
            }
        } else {
            throw new IllegalArgumentException("Invalid move: " + move);
        }
    }

    public TicTacToeGame deepCopy() {
        TicTacToeGame copy = new TicTacToeGame(player1, player2);
        copy.board = board.deepCopy();
        copy.turn = turn;
        return copy;
    }



    public String toString() {
        return board.toString();
    }

}
