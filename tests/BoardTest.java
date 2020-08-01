package tictactoe.tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import tictactoe.main.Board;
import tictactoe.main.IllegalGameMoveException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Tests a Board.
 */
public class BoardTest {

    private Board board;

    /**
     * Sets up the necessary objects that are needed during the testing.
     */
    @BeforeEach
    public void setup() {
        board = new Board();
    }

    /**
     * Asserts that when a board is just constructed, it is completely empty.
     */
    @Test
    public void constructedBoardIsEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assertions.assertThat(board.getField(i, j)).isEqualTo('_');
            }
        }
    }

    /**
     * Asserts that method getField does not allow illegal arguments for row and column.
     * @param row       The row number of the input to be asserted.
     * @param column    The column number of the input to be asserted.
     */
    @ParameterizedTest
    @CsvSource({"-1,1", "3,1", "1,-1", "1,3"})
    public void getFieldAllowsOnlyValidRowColumnNumbers(int row, int column) {
        assertThrows(AssertionError.class, () -> {
            board.getField(row, column);
        });
    }

    /**
     * Asserts that method setField does not allow illegal arguments for row and column.
     * @param row       The row number of the input to be asserted.
     * @param column    The column number of the input to be asserted.
     */
    @ParameterizedTest
    @CsvSource({"-1,1", "3,1", "1,-1", "1,3"})
    public void setFieldAllowsOnlyValidRowColumnNumbers(int row, int column) {
        assertThrows(AssertionError.class, () -> {
            board.getField(row, column);
        });
    }

    /**
     * Asserts that a legal move given to method setField, actually changes the board.
     */
    @Test
    public void setFieldLegalMoveChangesBoard() {
        try {
            board.setField(0, 1, 'X');
        } catch (IllegalGameMoveException e) {
            e.printStackTrace();
            fail();
        }
        Assertions.assertThat(board.getField(0, 1)).isEqualTo('X');

    }

    /**
     * Asserts that an illegal move given to method setField, makes the method
     * throw an IllegalGameMoveException.
     */
    @Test
    public void setFieldIllegalMoveThrowsException() {
        try {
            board.setField(0, 1, 'X');
        } catch (IllegalGameMoveException e) {
            e.printStackTrace();
            fail();
        }

        assertThrows(IllegalGameMoveException.class, () -> {
            board.setField(0, 1, 'O');
        });
    }

    /**
     * Tests the equals method with various inputs, but "this" remains the same.
     * The first input tests an object that is expected to be equal. The second input tests a
     * Board that should not be equal. The third input tests an object other than Board and should thus
     * return false. Arguments can be found at {@link #equalsProvideArguments() equalsProvideArguments} method.
     * @param first   a Board, will function as "this".
     * @param second  the object to which "this" will be compared to.
     * @param expected a boolean representing the expected output of the equals method.
     */
    @ParameterizedTest
    @MethodSource("equalsProvideArguments")
    public void equals(Board first, Object second, boolean expected) {
        Boolean equals = first.equals(second);
        Assertions.assertThat(equals).isEqualTo(expected);
    }

    /**
     * Provides arguments for {@link #equals(Board, Object, boolean) equals} method.
     * @return a Stream of arguments which will be used by "equals" method.
     */
    public static
    Stream<Arguments> equalsProvideArguments() {
        Board board = new Board();
        Board equals1 = new Board();
        Board equals2 = new Board();
        try {
            equals2.setField(2,1, 'X');
        } catch (IllegalGameMoveException e) {
            e.printStackTrace();
        }
        String equals3 = "String";
        return Stream.of(
                Arguments.of(board, equals1, true),
                Arguments.of(board, equals2, false),
                Arguments.of(board, equals3, false)
        );
    }

}

