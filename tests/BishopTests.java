package tests;
import main.*;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author kaichenle
 *
 */
public class BishopTests {
	/**
	 * test the moveChess function
	 */
	@Test
	public void shouldMoveBishop(){
		Board board = new Board();
		assertFalse("Knight out of bound", board.moveChess(2, 7, 1, 8));
		assertFalse("Knight not move", board.moveChess(2, 7, 2, 7));
		assertFalse("Knight jump over chesses", board.moveChess(2, 7, 4, 5));
		assertFalse("Knight move against convention", board.moveChess(2, 7, 5, 5));
		board.removePieces(3, 6);
		assertTrue("Knight move without capture", board.moveChess(2, 7, 4, 5));
		board.moveChess(1, 1, 1, 3);
		assertTrue("Knight move with capture", board.moveChess(4, 5, 2, 3));
	}
}
