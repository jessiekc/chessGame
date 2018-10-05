package tests;

import main.Board;
import main.Crab;
import main.Rook;
import org.junit.Test;

import static org.junit.Assert.*;

public class CrabTests {
    @Test
    public void shouldMoveCrab(){
        Board board = new Board();

        board.removePieces(1,7);
        board.board[1][7]  = new Crab(1,7, 2);
        assertFalse("Crab moved on another pawn with same color", board.moveChess(1, 7, 0, 7));
        board.removePieces(1,0);
        board.board[1][0]  = new Crab(1,0, 2);
        assertTrue("Crab capture another chess", board.moveChess(1, 0, 0, 0));
    }
    /**
     * test on isMovable
     */
    @Test
    public void isMovableCrab(){
        Board board = new Board();

        board.removePieces(1,7);
        board.board[1][7]  = new Crab(1,7, 2);
        assertFalse("Ox is not movable", board.getChessByPos(1,7).isMovable(board));
        board.removePieces(1,0);
        board.board[1][0]  = new Crab(1,0, 2);
        assertTrue("Ox is movable by capturing another chess", board.getChessByPos(1,0).isMovable(board));
    }

    @Test
    public void couldBeStoppedOx(){
        Board board = new Board();

        board.removePieces(1,6);
        board.board[1][6]  = new Crab(1,6, 2);
        board.board[3][3]  = new Rook(3, 3, 1);
        assertTrue("Ox could be stopped", board.getChessByPos(1,6).couldBeStopped(5,6, board));
    }

}
