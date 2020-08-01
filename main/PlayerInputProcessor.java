package tictactoe.main;

/**
 * Is able to process user input. Makes relevant changes on the given Board.
 * It is assumed that the player gives two ints, they don't have to be valid ints.
 */
public class PlayerInputProcessor {

    /**
     * The Board on which the input must be processed.
     */
    private Board board;

    /**
     * Constructor. Constructs a PlayerInputProcessor.
     * @param board the Board on which this PlayerInputProcessor should operate.
     */
    public PlayerInputProcessor(Board board) {
        this.board = board;
    }

    public static final String occupied = "This cell is occupied! Choose another one!";

    public static final String range = "Coordinates should be from 1 to 3!";

    public static final String ok = "ok";

    public static final String error = "error";

    /**
     * The row of the entry that should be changed. In internal representation.
     */
    private int row;

    /**
     * The column of the entry that should be changed. In internal representation.
     */
    private int column;

    /**
     * Processes the input from the user concerning where to place their next move.
     * This method assumes that the user gives ints. The issue where the user gives non-ints will be handled
     * in Main.
     * @param row     The row that should be changed according to the user.
     * @param column  The column that should be changed according to the user.
     * @param player  The player who wants to make the move. Can only be 'X' or 'O'.
     * @return        A String representing whether the action was successful.
     */
    public String process(int row, int column, char player) {
        if(row < 1 || row > 3 || column < 1 || column > 3) {
            return range;
        }

        translate(row, column);
        if(board.getField(this.row, this.column) != '_') {
            return occupied;
        }

        try {
            board.setField(this.row, this.column, player);
            return ok;
        } catch (IllegalGameMoveException e) {
            e.printStackTrace();
            return error;
        }
    }

    /**
     * Translates the row and column number from the users perspective to the
     * internal representation. Sets the corresponding fields {@link #row row} and {@link #column column}
     * to the correct internal representation.
     * @param row    The row number in external representation.
     * @param column The column number in external representation.
     */
    private void translate(int row, int column) {
        this.row = translateRow(row);
        this.column = translateColumn(column);
    }

    /**
     * Translates the row number from external representation to internal representation.
     * Returns the internal representation of the row. If a number other than 1, 2 or 3 is given, -1
     * will be returned.
     * @param row  The row number in external representation.
     * @return The row number in internal representation.
     */
    private int translateRow(int row) {
        if (row == 3) {
            return 0;
        }
        else if (row == 2) {
            return 1;
        }
        else if (row == 1) {
            return 2;
        }
        return -1;
    }

    /**
     * Translates the column number from external representation to internal representation.
     * Returns the internal representation of the column.
     * @param column The column number in external representation.
     * @return The column number in internal representation.
     */
    private int translateColumn(int column) {
        return column - 1;
    }
}
