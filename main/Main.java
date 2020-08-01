package tictactoe.main;

import java.util.Scanner;

public class Main {

    private static String enterCoordinates = "Enter the coordinates: ";

    private static String noNumbers = "You should enter numbers!";

    /**
     * The column of the entry where the next move will be put.
     */
    private static int column;

    /**
     * The row of the entry where the next will be put.
     */
    private static int row;

    /**
     * Boolean indicating if the game still is in progress.
     */
    private static boolean gameInProgress = true;

    /**
     * The player whose turn it is to make a move.
     */
    private static char player = 'X';

    /**
     * Scanner which will scan the user input.
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * The Board as given by the user. It may be updated as the user makes moves.
     */
    private static Board board;

    /**
     * Will determine the state of the Board.
     */
    private static BoardAnalyser analyser;

    /**
     * Will process the users input concerning where the next move should be made.
     */
    private static PlayerInputProcessor processor;


    /**
     * Processes the userInput and prints the board requested by the user on the screen.
     * Also processes the user input concerning making a move.
     * @param args  The requirements of the board given to the program.
     */
    public static void main(String[] args) {
        board = new Board();
        analyser = new BoardAnalyser(board);
        System.out.println(board.toString());
        while(gameInProgress) {
            processMove(false, player);
            if (player == 'X') {
                player = 'O';
            }
            else {
                player = 'X';
            }
            String status = analyser.analyse();
            if (!status.equals(BoardAnalyser.notFinished)) {
                System.out.println(status);
                gameInProgress = false;
            }
        }

    }

    /**
     * Processes the user input concerning making a move on the Board.
     * @param moveMade boolean indicating if a move has already been made.
     * @param player   The player who wants to make a move
     */
    private static void processMove(boolean moveMade, char player) {
        while(!moveMade) {
            System.out.print(enterCoordinates);
            String columnInput = scanner.next();
            String rowInput = scanner.next();

            try {
                column = Integer.parseInt(columnInput);
                row = Integer.parseInt(rowInput);
            } catch (NumberFormatException e) {
                System.out.println(noNumbers);
                continue;
            }

            processor = new PlayerInputProcessor(board);
            String message = processor.process(row, column, player);
            if (!message.equals(PlayerInputProcessor.ok)) {
                System.out.println(message);
            }
            else {
                System.out.println(board.toString());
                moveMade = true;
            }

        }
    }


}
