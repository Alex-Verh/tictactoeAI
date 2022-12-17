package ss.tictactoe.ai;

import ss.tictactoe.model.*;
import ss.tictactoe.ui.HumanPlayer;

import java.util.ArrayList;
import java.util.List;

public class SmartStrategy implements Strategy {
    /**
     * Creates a new strategy with the given name.
     *
     * @return strategy name
     */
    @Override
    public String getName() {
        return "SmartStrategy";
    }

    /**
     * Determines the next move, if the game still has available moves.
     *
     * @param game the current game
     * @return the player's choice
     */
    @Override
    public Move determineMove(Game game) {
        Move winning = findWinningMove(game);
        if (winning != null) {
            return winning;
        } else {
            return checkPlayerWinMove(game);
        }
    }


    public Move checkPlayerWinMove(Game game) {
        TicTacToeGame ticgame = (TicTacToeGame) game;
        TicTacToeGame copy = ticgame.deepCopy();
        List <Move> listMoves = new ArrayList<>();
        for (Move move : copy.getValidMoves()) {
            copy.doMove(move);
            Move moveplayer = findWinningMove(copy);
            if (moveplayer == null) {
                if (move != null) {
                    listMoves.add(move);
                    copy = ticgame.deepCopy();
                }
            } else {
                copy = ticgame.deepCopy();
            }
        }
        if (listMoves.size() > 0) {
            return listMoves.get((int) (Math.random() * listMoves.size()));
        }
        return game.getValidMoves().get((int) (Math.random() * game.getValidMoves().size()));
    }

    public Move findWinningMove(Game game) {
        TicTacToeGame ticgame = (TicTacToeGame) game;
        TicTacToeGame copy = ticgame.deepCopy();
        for (Move move : copy.getValidMoves()) {
            copy.doMove(move);
            if (copy.isGameover()) {
                return move;
            }
            copy = ticgame.deepCopy();
        }
        return null;
    }
}

