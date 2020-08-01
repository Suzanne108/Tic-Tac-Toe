package tictactoe.main;


/**
 * Represents a playing board in a tic-tac-toe game.
 * When speaking of a field on the board, we first mention the row and then the column.
 * For example field 2x1 represents the field in the second row and the first column.
 * When counting rows and columns, we start from zero.
 */
public class Board {

    /**
     * An array of characters representing the board.
     */
    private char[][] game;

    /**
     * An error message that will be used whenever an incorrect row number is given by a user.
     */
    private static final String incorrectRowNumber = "The row number does not exist on the board.";

    /**
     * An error message that will be used whenever an incorrect column number is given by a user.
     */
    private static final String incorrectColumnNumber = "The column number does not exist on the board.";

    /**
     * Static and final String which represents the boards boundaries.
     */
    private static final String boundaryBoard = "---------";

    /**
     * Constructor, initialises a board. Sets all field of the board to empty,
     * represented by the character '_'.
     */
    public Board() {
        game = new char[3][];

        //Create the arrays inside the main array
        for(int i = 0; i < 3; i++) {
            game[i] = new char[3];
        }

        //Set all fields to empty
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = '_';
            }
        }
    }

    /**
     * Gives the board as it is now.
     * @return the board.
     */
    public char[][] getBoard() {
        return game;
    }


    /**
     * Returns the character that populates the requested field.
     * @param row An int representing the number of the row the field is located in.
     * @param column An int representing the number of the column the field is located in.
     * @return a character that populates the requested field.
     */
    public char getField(int row, int column) {
        assert row < 3 && row >= 0 : incorrectRowNumber;
        assert column < 3 && column >= 0 : incorrectColumnNumber;
        return game[row][column];
    }

    /**
     * Sets the requested field to a given character. The only legal characters being 'X' and 'O'.
     * @param row       An int representing the row of the field that should be set.
     * @param column    An int representing the column of the field that should be set.
     * @param player    A char representing which character the field should be set to.
     * @throws IllegalGameMoveException if an illegal move is attempted. Namely when an attempt is made to set a field
     * which was already populated.
     */
    public void setField(int row, int column, char player) throws IllegalGameMoveException {
        assert row < 3 && row >= 0 : incorrectRowNumber;
        assert column < 3 && column >= 0 : incorrectColumnNumber;
        assert player == 'X' || player == 'O' : "Illegal character is attempted to be set to a field.";
        if (game[row][column] != '_') {
            throw new IllegalGameMoveException("This field is already populated and therefore the value cannot be changed.");
        }
        else {
            game[row][column] = player;
        }
    }

    /**
     * Returns a String representation of this Board.
     * @return a String representation of this Board.
     */
    @Override
    public String toString() {
        String board = boundaryBoard + "\n";
        for (int i = 0; i < 3; i++) {
            board = board + "| ";
            for (int j = 0; j < 3; j++) {
                board = board + game[i][j] + " ";
            }
            board = board + "|" + "\n";
        }
        board = board + boundaryBoard;
        return board;
    }

    /**
     * Compares this Board with another object. Returns true if the object is equal to this Board.
     * Returns false otherwise.
     * @param o  the object to which this Board is being compared to.
     * @return true if o is equal to this, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        Board object;
        if (o instanceof Board) {
            object = (Board) o;
        }
        else {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.getField(i, j) != object.getField(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

}
