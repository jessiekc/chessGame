package tests;
import main.*;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 *
 * @author kaichenle
 *
 */
public class UndoTests {
    @Test
    public void undoTest(){
        Board board = new Board();
        assertFalse("when stack is empty",board.undoValid());
        board.moveChess(1, 6, 1, 4);
        board.moveChess(0, 1, 0, 3);
        assertTrue("one undo",board.undoValid());
        board.moveChess(2, 6, 2, 4);
        board.moveChess(2, 1, 2, 3);
        board.undo();
        board.undo();
        assertFalse("when stack is empty",board.undoValid());
    }
    @Test
    public void getTurnsTest(){
        Board board = new Board(true);
        assertTrue("play 1 turn",board.getTurns ());
        board.switchTurns();
        assertFalse("play 2 turn",board.getTurns ());
    }
}
