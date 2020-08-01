package tictactoe.tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.main.Board;
import tictactoe.main.BoardFactory;
import tictactoe.main.IllegalGameMoveException;

/**
 * Provides tests for BoardFactory.
 */
public class BoardFactoryTest {

    private BoardFactory boardFactory;

    @BeforeEach
    public void setup() {
        boardFactory = new BoardFactory();
    }

    /**
     * Tests if BoardFactory creates a Board as it should be if a Board is given in which X wins.
     */
    @Test
    public void createBoardXWins() {
        String xWins = "XXXOO____";
        Board result = boardFactory.createBoard(xWins);
        Board expected = new Board();
        try {
            expected.setField(0,0, 'X');
            expected.setField(0, 1, 'X');
            expected.setField(0, 2, 'X');
            expected.setField(1, 0, 'O');
            expected.setField(1, 1, 'O');
        } catch (IllegalGameMoveException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
