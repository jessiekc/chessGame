package tests;
import main.*;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author kaichenle
 *
 */
public class QueenTests {
	/**
	 * test on moveChess
	 */
	@Test
	public void shouldMoveQueen(){
		Board board = new Board();

		assertFalse("Queen not move", board.moveChess(3, 7, 3, 7));
		assertFalse("Queen on to chess with same color", board.moveChess(3, 7, 2, 7));
		board.removePieces(3, 6);
		assertTrue("Queen move forward", board.moveChess(3, 7, 3, 5));
		assertFalse("Queen against convention", board.moveChess(3, 5, 4, 7));
		
	}
}
