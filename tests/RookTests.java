package tests;
import main.*;
import static org.junit.Assert.*;

import org.junit.Test;
public class RookTests {
	@Test
	public void shouldMoveRook(){
		Board board = new Board();
		assertFalse("Knight moved on another pawn with same color", board.moveChess(0, 7, 0, 6));
		assertFalse("Knight jump over chess", board.moveChess(0, 7, 0, 5));
		board.removePieces(0, 6);
		assertTrue("Knight moved forward and capture chess", board.moveChess(0, 7, 0, 1));
		assertTrue("Knight moved right and capture chess", board.moveChess(0, 1, 1, 1));
		assertFalse("Knight moved outofbound", board.moveChess(1, 1, -1, 1));
		assertFalse("Knight not move", board.moveChess(1, 1, 1, 1));
		assertFalse("Knight not move inline", board.moveChess(1, 1, 5, 5));
	}

}
