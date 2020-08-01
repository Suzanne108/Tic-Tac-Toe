package tictactoe.main;

/**
 * Is given a Board. Can analyse the board to determine the state of the game.
 */
public class BoardAnalyser {

    /**
     * The Board that is analysed by this analyser.
     */
    private Board board;

    public static final String notFinished = "Game not finished";

    public static final String draw = "Draw";

    public static final String xWins = "X wins";

    public static final String oWins = "O wins";

    public static final String impossible = "Impossible";

    /**
     * The amount of X entries on the board.
     */
    private int amountX;

    /**
     * The amount of O entries on the board.
     */
    private int amountO;

    /**
     * The amount of _ entries on the board.
     */
    private int amount_;

    /**
     * Boolean indicating if X has three entries in a row, in any direction.
     */
    private boolean xHasThreeInRow;

    /**
     * Boolean indicating if O has three entries in a row, in any direction.
     */
    private boolean oHasThreeInRow;

    /**
     * Constructor. Constructs a BoardAnalyser.
     * @param board  the Board to be analysed by this BoardAnalyser.
     */
    public BoardAnalyser(Board board) {
        this.board = board;
    }

    /**
     * Analyses the given Board.
     * Returns the appropriate state of the Board.
     * @return a String representing the current state of the Board.
     */
    public String analyse() {
        countAll();
        if (Math.abs(amountX - amountO) >= 2) {
            return impossible;
        }
        hasThreeInRow('X');
        hasThreeInRow('O');
        if (oHasThreeInRow && xHasThreeInRow) {
            return impossible;
        }
        else if (oHasThreeInRow) {
            return oWins;
        }
        else if (xHasThreeInRow) {
            return xWins;
        }
        else {
            if (amount_ > 0) {
                return notFinished;
            }
            else {
                return draw;
            }
        }

    }

    /**
     * Counts all possible characters.
     */
    private void countAll() {
        count('X');
        count('O');
        count('_');
    }


    /**
     * Counts the amount of entries from the corresponding character.
     * Sets the corresponding field variable to the correct amount.
     * So if 'X' is given as character, {@link #amountX amountX} will be updated.
     * Only 'X', 'O', '_', are valid characters.
     * @param character the character that should be counted on the Board.
     */
    private void count(char character) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getField(i, j) == character) {
                    count++;
                }
            }
        }
        if (character == 'X') {
            amountX = count;
        }
        else if (character == 'O') {
            amountO = count;
        }
        else if (character == '_') {
            amount_ = count;
        }
    }

    /**
     * Figures out if the given player has three entries in a row, in any direction.
     * Sets the corresponding field to the correct boolean value.
     * So if 'X' is given as input, {@link #xHasThreeInRow xHasThreeInRow} will be set.
     * @param player  a character indicating for which player
     *                it should be analysed if he/she has three in a row.
     *                Only 'X' and 'O' can be given.
     */
    private void hasThreeInRow(char player){
        if (player == 'X') {
            xHasThreeInRow = checkColumnsAndRowsForWinner(player) || checkDiagonalsForWinner(player);
        }
        else if (player == 'O') {
            oHasThreeInRow = checkColumnsAndRowsForWinner(player) || checkDiagonalsForWinner(player);
        }
    }


    /**
     * Checks the columns and rows if a certain player has won the game.
     * Sets the corresponding boolean field to true if the player has won.
     * This method will not set the corresponding boolean field to false in any case.
     * The overall winning of a certain player is handled by {@link #hasThreeInRow(char) hasThreeInRow}.
     * @param player a character representing for which player it must be checked if he/she has won.
     */
    private boolean checkColumnsAndRowsForWinner(char player) {
        for(int i = 0; i < 3; i++) {
            int counterColumns = 0;
            int counterRows = 0;
            for(int j = 0; j < 3; j++) {
                if (board.getField(j, i) == player) {
                    counterColumns++;
                }
                if (board.getField(i, j) == player) {
                    counterRows++;
                }
            }
            if (counterColumns == 3 || counterRows == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the diagonals if a certain player has won the game.
     * Sets the corresponding boolean field to true if the player has won.
     * This method will not set the corresponding boolean field to false in any case.
     * The overall winning of a certain player is handled by {@link #hasThreeInRow(char) hasThreeInRow}
     * @param player a character representing for which player it must be checked if he/she has won.
     */
    private boolean checkDiagonalsForWinner(char player) {
        int countLeftRight = 0;
        int countRightLeft = 0;
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i + j == 2) {
                    if (board.getField(i, j) == player) {
                        countRightLeft++;
                    }
                }
                if (i + j == 0 || i + j == 4) {
                    if (board.getField(i, j) == player) {
                        countLeftRight++;
                    }
                }
            }
        }

        if(countLeftRight == 2 && board.getField(1,1 ) == player) {
            return true;
        }

        return countRightLeft == 3;
    }

}
