package tictactoe.main;

/**
 * IllegalGameMoveException is an exception that will be thrown whenever a user
 * attempts to make a move that is not legal.
 */
public class IllegalGameMoveException extends Exception {

    /**
     * Constructs an IllegalGameMoveException with the given error message.
     * @param errorMessage A String containing the error messages to be given when this exception is thrown.
     */
    public IllegalGameMoveException(String errorMessage) {
        super(errorMessage);
    }
}
