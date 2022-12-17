package ss.tictactoe.ai;

import ss.tictactoe.model.Game;
import ss.tictactoe.model.Move;

public interface Strategy {



    /**
     * Creates a new strategy with the given name.
     * @return strategy name
     */
    //@ pure
    public String getName();

    /**
     * Determines the next move, if the game still has available moves.
     * @param game the current game
     * @return the player's choice
     */

    //@ requires game != null;
    //@ requires !game.isGameover();
    //@ ensures game.isValidMove(\result);
    public Move determineMove(Game game);

}
