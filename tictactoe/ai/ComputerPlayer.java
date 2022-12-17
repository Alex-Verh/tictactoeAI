package ss.tictactoe.ai;

import ss.tictactoe.model.*;

public class ComputerPlayer extends AbstractPlayer {

    private final Mark mark;
    private final Strategy strategy;

    public ComputerPlayer(Mark mark, Strategy strategy) {
        super(strategy.getName());
        this.mark = mark;
        this.strategy = strategy;
    }


    /**
     * Determines the next move, if the game still has available moves.
     *
     * @param game the current game
     * @return the player's choice
     */
    @Override
    public Move determineMove(Game game) {
        TicTacToeMove move = (TicTacToeMove) strategy.determineMove(game);
        move.setMark(mark);
        return move;
    }
}
