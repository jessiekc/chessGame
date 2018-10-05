package tests;
import main.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//Given[ExplainYourInput]When[WhatIsDone]Then[ExpectedResult]
//ordersShouldBeCreated
/**
 * 
 * @author kaichenle
 *
 */
public class KingTests {
	/**
	 * test on moveChess
	 */
	@Test
	public void shouldMoveKing(){
		Board board = new Board();

		//invalid input for moving out of grid
		assertFalse("King moved out of grid", board.moveChess(4, 7, 4, 8));
		//invalid input for moving 2 grid
		board.removePieces(4, 6);
		assertFalse("King moved 2 steps forward", board.moveChess(4, 7, 4, 5));
		//move forward
		assertTrue("King moved 1 steps forward", board.moveChess(4, 7, 4, 6));
		//move diagnal
		assertTrue("King moved 1 step diagnal", board.moveChess(4, 6, 3, 5));
		assertFalse("King not moving", board.moveChess(3, 5, 3, 5));
	}

	//this function will never be called, implement it because the abstract.
	@Test
	public void isMovableKing(){
		Board board = new Board();

		board.getChessByPos(4,7).isMovable(board);
		board.getChessByPos(4,7).couldBeStopped(4,6,board);
		board.moveChess(4, 7, 4, 8);
	}
}
