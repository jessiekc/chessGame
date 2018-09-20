package tests;
import main.*;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author kaichenle
 *
 */
public class KnightTests {
	/**
	 * test on moveChess
	 */
	@Test
	public void shouldMoveKnight(){
		Board board = new Board();
		assertFalse("Knight moved on another pawn with same color", board.moveChess(1, 7, 3, 6));
		assertFalse("Knight not move", board.moveChess(1, 7, 1, 7));
		assertFalse("Knight moved out of grid", board.moveChess(1, 7, -1, 8));
		assertFalse("Knight moved against convention", board.moveChess(1, 7, 4, 3));
		assertTrue("Knight moved cross chess", board.moveChess(1, 7, 2, 5));
		board.moveChess(1, 1, 1, 3);
		assertTrue("Knight moved cross chess", board.moveChess(2, 5, 1, 3));
	}
}
