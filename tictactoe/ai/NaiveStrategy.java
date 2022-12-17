package ss.tictactoe.ai;

import ss.tictactoe.model.Game;
import ss.tictactoe.model.Move;

public class NaiveStrategy implements Strategy {

    /**
     * Creates a new strategy with the given name.
     *
     * @return strategy name
     */
    @Override
    public String getName() {
        return "NaiveStrategy";
    }

    /**
     * Determines the next move, if the game still has available moves.
     *
     * @param game the current game
     * @return the player's choice
     */
    @Override
    public Move determineMove(Game game) {
        return game.getValidMoves().get((int) (Math.random() * game.getValidMoves().size()));
    }
}
