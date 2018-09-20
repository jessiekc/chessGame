package tests;
import main.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
/**
 * 
 * @author kaichenle
 *
 */
public class PawnTests {
	/**
	 * test on moveChess
	 */
	@Test
	public void shouldMovePawn(){
		Board board = new Board();
		assertFalse("Pawn go diagnal without capture chess", board.moveChess(1, 6, 0, 5));
		assertTrue("Pawn go two on first step", board.moveChess(1, 6, 1, 4));
		assertFalse("Pawn agains convention", board.moveChess(1, 1, 2, 4));
		board.moveChess(1, 1, 1, 3);
		assertFalse("Pawn not going anywhere", board.moveChess(1, 3, 1, 3));
		assertFalse("Pawn go straight and try to capture chess", board.moveChess(1, 4, 1, 3));
		board.removePieces(1, 3);
		assertFalse("Pawn go two not on first step", board.moveChess(1, 4, 1, 2));
		assertFalse("Pawn go right or left", board.moveChess(1, 4, 2, 4));
		board.moveChess(2, 1, 2, 2);
		board.moveChess(0, 1, 0, 3);
		assertTrue("Pawn go diagnal and capture chess", board.moveChess(1, 4, 0, 3));
		board.removePieces(0, 0);
		board.moveChess(0, 3, 0, 2);
		board.moveChess(0, 2, 0, 1);
		board.moveChess(0, 1, 0, 0);
		assertFalse("Pawn go out of bound",board.moveChess(0, 0, 0, -1));
	}
}
