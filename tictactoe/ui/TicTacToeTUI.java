package ss.tictactoe.ui;

import ss.tictactoe.ai.ComputerPlayer;
import ss.tictactoe.ai.NaiveStrategy;
import ss.tictactoe.ai.SmartStrategy;
import ss.tictactoe.ai.Strategy;
import ss.tictactoe.model.*;

import java.util.Scanner;

public class TicTacToeTUI {

    private static boolean winner(Game game) {
        if (game.isGameover()) {
            if (game.getWinner() == null) {
                System.out.println("Draw!");
            } else {
                System.out.println("Winner: " + game.getWinner());
            }
            return true;
        }
        return false;
    }

    private static boolean playagain() {
        System.out.println("Do you want to play again? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        return answer.toLowerCase().contains("y");
    }

    private static void run() {
        boolean exit = false;
        System.out.println("Please enter the name of the first player");
        Scanner name = new Scanner(System.in);
        String name1 = name.nextLine();
        System.out.println("Please enter the name of the second player");
        String name2 = name.nextLine();

        AbstractPlayer player1 = new HumanPlayer(name1, Mark.XX);
        AbstractPlayer player2 = new HumanPlayer(name2, Mark.OO);
        if (name1.toUpperCase().equals("-N")) {
            player1 = new ComputerPlayer(Mark.XX, new NaiveStrategy());
        }
        if (name2.toUpperCase().equals("-N")) {
            player2 = new ComputerPlayer(Mark.OO, new NaiveStrategy());
        }
        if (name1.toUpperCase().equals("-S")) {
            player1 = new ComputerPlayer(Mark.XX, new SmartStrategy());
        }
        if (name2.toUpperCase().equals("-S")) {
            player2 = new ComputerPlayer(Mark.OO, new SmartStrategy());
        }

        Game game = new TicTacToeGame(player1, player2);
        while (!exit) {
            while (!winner(game)) {
                System.out.println(game);
                AbstractPlayer turn = (AbstractPlayer) game.getTurn();
                Move move = turn.determineMove(game);
                game.doMove(move);
            }
            System.out.println(game);
            exit = !playagain();
            game = new TicTacToeGame(player1, player2);

        }
    }

    public static void main(String[] args) {
        run();
    }
}
