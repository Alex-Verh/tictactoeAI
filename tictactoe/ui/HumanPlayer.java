package ss.tictactoe.ui;

import ss.tictactoe.model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {

    private Mark mark;


    public HumanPlayer(String name, Mark mark) {
        super(name);
        this.mark = mark;
    }

    /**
     * Determines the next move, if the game still has available moves.
     *
     * @param game the current game
     * @return the player's choice
     */
    @Override
    public Move determineMove(Game game) {
        while (true) {
            System.out.println("Please enter the index of the field you want to play");
            Scanner index = new Scanner(System.in);
            int index1 = -1;
            try {
                index1 = index.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                continue;
            }
            Move move = new TicTacToeMove(index1 / 3, index1 % 3, mark);
            if (index1 < 0 || index1 > 8 || !game.isValidMove(move)) {
                System.out.println("Please enter a valid index");
            } else {
                return move;
            }
        }
    }
}
