package tictactoe;

/**
 * This class has functionality to create boards given a different representation of a board.
 */
public class BoardFactory {

    /**
     * Constructs a BoardFactory
     */
    public BoardFactory() {}

    /**
     * This method is given a String as input, consisting of nine characters.
     * Each three consecutive characters representing one row of the board.
     * From this String a Board is created and returned.
     * @param input  The String representing the Board that should be created.
     * @return a Board corresponding to the given template.
     */
    public Board createBoard(String input) {
        Board board = new Board();
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                char occupier = input.charAt((3*i) + j);
                if (occupier != '_') {
                    try {
                        board.setField(i, j, occupier);
                    } catch (IllegalGameMoveException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return board;
    }

}
