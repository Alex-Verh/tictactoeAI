package ss.tictactoe.model;

public class TicTacToeMove implements Move {
    private int row;
    private int col;
    private Mark mark;

    public TicTacToeMove(int row, int col, Mark mark) {
        this.row = row;
        this.col = col;
        this.mark = mark;
    }

    public TicTacToeMove(int row, int col) {
        this.row = row;
        this.col = col;
        this.mark = Mark.EMPTY;
    }

    public Mark getMark() {
        return mark;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "move: index: " + (row * 3 + col);
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
