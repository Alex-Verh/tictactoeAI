package ss.tictactoe.model;

/**
 * Board for the Tic Tac Toe game.
 */
public class Board {
    /*@ public invariant fields.length == DIM*DIM;
        public invariant (\forall int i; (i >= 0 && i < DIM*DIM); fields[i] == Mark.EMPTY || fields[i] == Mark.XX || fields[i] == Mark.OO);
    @*/

    /**
     * Dimension of the board, i.e., if set to 3, the board has 3 rows and 3 columns.
     */
    public static final int DIM = 3;
    private static final String DELIM = "     ";
    private static final String[] NUMBERING = {" 0 | 1 | 2 ", "---+---+---",
        " 3 | 4 | 5 ", "---+---+---", " 6 | 7 | 8 "};
    private static final String LINE = NUMBERING[1];

    /**
     * The DIM by DIM fields of the Tic Tac Toe board. See NUMBERING for the
     * coding of the fields.
     */
    private Mark[] fields;

    // -- Constructors -----------------------------------------------
    /**
     * Creates an empty board.
     */
    //@ ensures (\forall int i; (i >= 0 && i < DIM*DIM); fields[i] == Mark.EMPTY);
    public Board() {
        fields = new Mark[DIM * DIM];
        for (int i = 0; i < DIM * DIM; i++) {
            fields[i] = Mark.EMPTY;
        }
    }

    /**
     * Creates a deep copy of this field.
     */
    /*@ ensures \result != this;
     ensures (\forall int i; (i >= 0 && i < DIM*DIM); \result.fields[i] == this.fields[i]);
     @*/
    public Board deepCopy() {
        Board deepCopy = new Board();
        for (int i = 0; i < DIM * DIM; i++) {
            deepCopy.setField(i, this.getField(i));
        }
         return deepCopy;
    }

    /**
     * Calculates the index in the linear array of fields from a (row, col)
     * pair.
     * @return the index belonging to the (row,col)-field
     */
    /*@ requires row >= 0 && row < DIM;
    requires col >= 0 && row < DIM;
     @*/
    public int index(int row, int col) {
    	 int index = row * DIM + col;
         return index;
    }

    /**
     * Returns true if index is a valid index of a field on the board.
     * @return true if 0 <= index < DIM*DIM
     */
    //@ ensures index >= 0 && index < DIM*DIM ==> \result == true;
    public boolean isField(int index) {
        return index >= 0 && index < DIM * DIM;
    }

    /**
     * Returns true of the (row,col) pair refers to a valid field on the board.
     * @return true if 0 <= row < DIM && 0 <= col < DIM
     */
    //@ ensures row >= 0 && row < DIM && col >= 0 && col < DIM ==> \result == true;
    public boolean isField(int row, int col) {
        return row >= 0 && col >= 0 && row < DIM && col < DIM;
    }
    
    /**
     * Returns the content of the field i.
     * @param i the number of the field (see NUMBERING)
     * @return the mark on the field
     */
    /*@ requires isField(i);
    ensures \result == Mark.EMPTY || \result == Mark.OO || \result == Mark.XX;
     @*/
    public Mark getField(int i) {
        return fields[i];
    }

    /**
     * Returns the content of the field referred to by the (row,col) pair.
     * @param row the row of the field
     * @param col the column of the field
     * @return the mark on the field
     */
    /*@ requires isField(row, col);
    ensures \result == Mark.EMPTY || \result == Mark.OO || \result == Mark.XX;
     @*/
    public Mark getField(int row, int col) {
        return fields[index(row, col)];
    }

    /**
     * Returns true if the field i is empty.
     * @param i the index of the field (see NUMBERING)
     * @return true if the field is empty
     */
    /*@ requires isField(i);
    ensures getField(i) == Mark.EMPTY ==> \result == true;
     @*/
    public boolean isEmptyField(int i) {
        return fields[i] == Mark.EMPTY;
    }

    /**
     * Returns true if the field referred to by the (row,col) pair it empty.
     * @param row the row of the field
     * @param col the column of the field
     * @return true if the field is empty
     */
    /*@ requires isField(row, col);
    ensures getField(row, col) == Mark.EMPTY ==> \result == true;
     @*/
    public boolean isEmptyField(int row, int col) {
        return fields[index(row, col)] == Mark.EMPTY;
    }

    /**
     * Tests if the whole board is full.
     * @return true if all fields are occupied
     */
    //@ ensures (\forall int i; (i >= 0 && i < DIM*DIM); fields[i] == Mark.XX || fields[i] == Mark.OO);
    public boolean isFull() {
        for (int i = 0; i < DIM * DIM; i++) {
            if (fields[i] == Mark.EMPTY) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the game is over. The game is over when there is a winner
     * or the whole board is full.
     * @return true if the game is over
     */
    //@ ensures isFull() || hasWinner() ==> \result == true;
    public boolean gameOver() {
        return isFull() || hasWinner();
    }

    /**
     * Checks whether there is a row which is full and only contains the mark
     * m.
     * @param m the Mark of interest
     * @return true if there is a row controlled by m
     */
    public boolean hasRow(Mark m) {
        for (int i = 0; i < DIM; i++) {
            boolean row = true;
            for (int j = 0; j < DIM; j++) {
                row = row && getField(i, j) == m;
            }
            if (row) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether there is a column which is full and only contains the mark
     * m.
     * @param m the Mark of interest
     * @return true if there is a column controlled by m
     */
    public boolean hasColumn(Mark m) {
        for (int j = 0; j < DIM; j++) {
            boolean col = true;
            for (int i = 0; i < DIM; i++) {
                col = col && getField(i, j) == m;
            }
            if (col) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether there is a diagonal which is full and only contains the
     * mark m.
     * @param m the Mark of interest
     * @return true if there is a diagonal controlled by m
     */
    public boolean hasDiagonal(Mark m) {
        boolean diag = true;
        for (int i = 0; i < DIM; i++) {
            diag = diag && getField(i, i) == m;
        }
        if (diag) {
            return true;
        }
        diag = true;
        for (int i = 0; i < DIM; i++) {
            diag = diag && getField(i, DIM - 1 - i) == m;
        }
        return diag;
    }

    /**
     * Checks if the mark m has won. A mark wins if it controls at
     * least one row, column or diagonal.
     * @param m the mark of interest
     * @return true if the mark has won
     */
    /*@ requires m == Mark.XX || m == Mark.OO;
    ensures hasRow(m) || hasColumn(m) || hasDiagonal(m) ==> \result == true;
     @*/
    public boolean isWinner(Mark m) {
    	 return hasRow(m) || hasColumn(m) || hasDiagonal(m);
    }

    /**
     * Returns true if the game has a winner. This is the case when one of the
     * marks controls at least one row, column or diagonal.
     * @return true if the student has a winner.
     */
    //@ ensures isWinner(Mark.XX) || isWinner(Mark.OO) ==> \result == true;
    public boolean hasWinner() {
    	 return isWinner(Mark.XX) || isWinner(Mark.OO);
    }

    /**
     * Returns a String representation of this board. In addition to the current
     * situation, the String also shows the numbering of the fields.
     *
     * @return the game situation as String
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < DIM; i++) {
            String row = rowString(i);
            s = s + row + DELIM + NUMBERING[i * 2];
            if (i < DIM - 1) {
                s = s + "\n" + LINE + DELIM + NUMBERING[i * 2 + 1] + "\n";
            }
        }
        return s;
    }

    private String rowString(int i) {
        String row = "";
        for (int j = 0; j < DIM; j++) {
            row += " " + getField(i, j).toString().substring(0, 1).replace("E", " ") + " ";
            if (j < DIM - 1) {
                row = row + "|";
            }
        }
        return row;
    }

    /**
     * Empties all fields of this board (i.e., let them refer to the value
     * Mark.EMPTY).
     */
    //@ ensures (\forall int i; (i >= 0 && i < DIM*DIM); fields[i] == Mark.EMPTY);
    public void reset() {
        for (int i = 0; i < DIM * DIM; i++) {
            fields[i] = Mark.EMPTY;
        }
    }

    /**
     * Sets the content of field i to the mark m.
     * @param i the field number (see NUMBERING)
     * @param m the mark to be placed
     */
    /*@ requires isField(i);
    ensures getField(i) == m;
     @*/
    public void setField(int i, Mark m) {
    	 fields[i] = m;
    }

    /**
     * Sets the content of the field represented by
     * the (row,col) pair to the mark m.
     * @param row the field's row
     * @param col the field's column
     * @param m the mark to be placed
     */
    /*@ requires isField(row, col);
    ensures getField(row, col) == m;
     @*/
    public void setField(int row, int col, Mark m) {
    	 fields[index(row, col)] = m;
    }

}
