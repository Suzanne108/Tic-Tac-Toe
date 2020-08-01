package tictactoe.tests;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tictactoe.main.Board;
import tictactoe.main.BoardAnalyser;
import tictactoe.main.BoardFactory;

import java.util.stream.Stream;

/**
 * Tests the BoardAnalyser with various tests.
 */
public class BoardAnalyserTest {

    private BoardAnalyser analyser;
    private BoardFactory factory;

    /**
     * Sets up the dependencies to create Boards.
     */
    @BeforeEach
    public void setup() {
        factory = new BoardFactory();
    }

    /**
     * Tests if BoardAnalyser can deliver the correct message given a certain Board.
     * A BoardFactory is used to generate the Board. The test cases are explained in the parameter generator
     * method {@link #generateParametersAnalyse() generateParametersAnalyse}.
     * @param boardGenerator the String from which the Board will be generated.
     * @param expected the String that the analyser is expected to return.
     */
    @ParameterizedTest
    @MethodSource("generateParametersAnalyse")
    public void analyse(String boardGenerator, String expected) {
        Board board = factory.createBoard(boardGenerator);
        analyser = new BoardAnalyser(board);
        Assertions.assertThat(analyser.analyse()).isEqualTo(expected);
    }

    /**
     * Provides arguments for test method {@link #analyse(String, String) anlyse}.
     * Individual test cases are explained in a comment.
     * @return a Stream of Arguments.
     */
    private static Stream<Arguments> generateParametersAnalyse() {
        return Stream.of(
                //Tests case impossible, there are too many X's compared to O's
                Arguments.of("XOX_X__XO", BoardAnalyser.impossible),
                //Tests case impossible, both O and X have three entries in a row
                Arguments.of("OOO___XXX", BoardAnalyser.impossible),
                //Test case O wins. O has three entries diagonal
                Arguments.of("O__XOX_XO", BoardAnalyser.oWins),
                //Test case X wins. X has three entries vertically
                Arguments.of("XO_X_OXO_", BoardAnalyser.xWins),
                //Test case game not finished. There is only one place left
                Arguments.of("XOXXXOO_O", BoardAnalyser.notFinished),
                //Test case draw
                Arguments.of("XOXXOXOXO", BoardAnalyser.draw)
        );
    }
}

