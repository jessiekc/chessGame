package tests;

import main.Board;
import main.Ox;
import main.Rook;
import org.junit.Test;

import static org.junit.Assert.*;

public class OxTests {
    /**
     * test on moveChess
     */
    @Test
    public void moveChessOx(){
        Board board = new Board();
        board.removePieces(1,7);
        board.board[1][7]  = new Ox(1,7, 2);
        assertFalse("Ox moved on another pawn with same color", board.moveChess(1, 7, 1, 6));
        board.removePieces(1,0);
        board.board[1][0]  = new Ox(1,0, 2);
        assertTrue("Ox capture another chess", board.moveChess(1, 0, 1, 1));
    }

    /**
     * test on isMovable
     */
    @Test
    public void isMovableOx(){
        Board board = new Board();
        board.removePieces(1,7);
        board.board[1][7]  = new Ox(1,7, 2);
        assertFalse("Ox is not movable", board.getChessByPos(1,7).isMovable(board));
        board.removePieces(1,0);
        board.board[1][0]  = new Ox(1,0, 2);
        assertTrue("Ox is movable by capturing another chess", board.getChessByPos(1,0).isMovable(board));
    }
    /**
     * test on couldBeStopped
     */
    @Test
    public void couldBeStoppedOx(){
        Board board = new Board();
        board.removePieces(1,6);
        board.board[1][6]  = new Ox(1,6, 2);
        board.board[0][3]  = new Rook(0, 3, 1);
        assertTrue("Ox could be stopped", board.getChessByPos(1,6).couldBeStopped(1,1, board));
    }

}
