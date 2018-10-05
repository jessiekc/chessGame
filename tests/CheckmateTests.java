package tests;
import main.*;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author kaichenle
 *
 */
public class CheckmateTests {
	/**
	 * test on isCheckedByRookOrQueen
	 */
	@Test
	public void isCheckedByHelperTest(){
		Board board = new Board();
		board.removePieces(1,3);
		board.removePieces(5,3);
		board.removePieces(6,4);
		board.removePieces(2,4);
		//remove everything except the king
		for (int i = 0; i < 8; i++) {
			board.removePieces(i, 6);
		}
		for (int i = 0; i < 8; i++) {
			if(i == 4) {
				continue;
			}
			board.removePieces(i, 7);
		}
		board.removePieces(4, 1);
		board.board[4][1]= new Rook(4, 1, 2);
		//test isCheckedByRookOrQueen 
		assertEquals(1, board.isCheckedByRookOrQueen(board.getKing(1)).size());
		board.removePieces(4, 1);
		board.board[2][5]= new Queen(2, 5, 1);
		board.board[3][6]= new Pawn(3, 6, 1);
		//test isCheckedBishopOrQueen 
		assertEquals(0, board.isCheckedByBishopOrQueen(board.getKing(2)).size());
		board.removePieces(3, 6);
		assertEquals(1, board.isCheckedByBishopOrQueen(board.getKing(2)).size());
		//test isCheckedByKnight
		board.removePieces(2, 5);
		board.board[5][5]= new Knight(5, 5, 1);
		assertEquals(1, board.isCheckedByKnight(board.getKing(2)).size());
		
	}
	
	/**
	 * test on couldStopBishopOrQueen
	 */
	@Test
	public void couldStopBishopOrQueenHelperTest(){
		Board board = new Board();
		board.removePieces(1,3);
		board.removePieces(5,3);
		board.removePieces(6,4);
		board.removePieces(2,4);
		//remove everything except the king
		for (int i = 0; i < 8; i++) {
			board.removePieces(i, 6);
		}
		for (int i = 0; i < 8; i++) {
			if(i == 4) {
				continue;
			}
			board.removePieces(i, 7);
		}
		board.board[2][5] = new Queen (2, 5, 1);
		board.board[2][6] = new Rook (2, 6, 2);
		assertTrue("test on couldStopBishopOrQueen", board.getChessByPos(2,5).couldBeStopped(4, 7, board));
		board.removePieces(2, 6);
		assertFalse("test on couldStopBishopOrQueen", board.getChessByPos(2,5).couldBeStopped(4, 7, board));
		board.removePieces(2, 5);
		board.board[6][5] = new Queen (6, 5, 1);
		assertFalse("test on couldStopBishopOrQueen",board.getChessByPos(6,5).couldBeStopped(4, 7, board));
		board.removePieces(6, 5);
//		board.removePieces(3, 1);
//		board.removePieces(5, 1);
		board.board[2][2] = new Queen (2, 2, 2);
		System.out.print(board.getChessByPos(2,2));
		assertFalse("test on couldStopBishopOrQueen", board.getChessByPos(2,2).couldBeStopped(3, 1, board));
		board.removePieces(2, 2);
		board.board[6][2] = new Queen (6, 2, 2);
		assertFalse("test on couldStopBishopOrQueen", board.getChessByPos(6,2).couldBeStopped(4, 0, board));
		board.removePieces(4, 0);
		board.board[4][4] = new King (4, 4, 1);
	}
	
	/**
	 * test on couldStopRookOrQueen
	 */
	@Test
	public void couldStopRookOrQueenHelperTest(){

		Board board = new Board();
		board.removePieces(1,3);
		board.removePieces(5,3);
		board.removePieces(6,4);
		board.removePieces(2,4);
		//remove everything except the king
		for (int i = 0; i < 8; i++) {
			board.removePieces(i, 6);
		}
		for (int i = 0; i < 8; i++) {
			if(i == 4) {
				continue;
			}
			board.removePieces(i, 7);
		}
		board.board[7][7] = new Rook (7, 7, 1);
		assertFalse("test on couldStopRookOrQueen", board.getChessByPos(7,7).couldBeStopped(4, 7, board));
		board.board[4][3] = new Rook (4, 3, 1);
		assertFalse("test on couldStopRookOrQueen", board.getChessByPos(4,3).couldBeStopped(4, 7, board));
		board.removePieces(4, 1);
		board.board[4][3] = new Rook (4, 3, 2);
		assertFalse("test on couldStopRookOrQueen", board.getChessByPos(4,3).couldBeStopped(4, 0, board));
	}
	
	/**
	 * test on rookOrQueenChecker
	 */
	@Test
	public void rookOrQueenCheckerHelperTest() {
		Board board = new Board();
		board.removePieces(1,3);
		board.removePieces(5,3);
		board.removePieces(6,4);
		board.removePieces(2,4);
		//remove everything except the king
		for (int i = 0; i < 8; i++) {
			board.removePieces(i, 6);
		}
		for (int i = 0; i < 8; i++) {
			if(i == 4) {
				continue;
			}
			board.removePieces(i, 7);
		}
		board.board[7][7] = new Rook (7, 7, 1);
		assertFalse("test on rookOrQueenCheckerHelperTest",(board.isCheckedByRookOrQueen(board.getChessByPos(4, 7)).size())==0);
		board.removePieces(7, 7);
		board.board[4][4] = new Rook (4, 4, 1);
		assertFalse("test on rookOrQueenCheckerHelperTest",(board.isCheckedByRookOrQueen(board.getChessByPos(4, 7)).size())==0);
		board.removePieces(4, 4);
		board.removePieces(4, 1);
		board.board[4][3] = new Rook (4, 3, 2);
		assertFalse("test on rookOrQueenCheckerHelperTest",(board.isCheckedByRookOrQueen(board.getChessByPos(4, 0)).size())==0);
	}
	
	/**
	 * test on knightChecker
	 */
	@Test
	public void knightCheckerHelperTest() {
		Board board = new Board();
		board.removePieces(1,3);
		board.removePieces(5,3);
		board.removePieces(6,4);
		board.removePieces(2,4);
		//remove everything except the king
		for (int i = 0; i < 8; i++) {
			board.removePieces(i, 6);
		}
		for (int i = 0; i < 8; i++) {
			if(i == 4) {
				continue;
			}
			board.removePieces(i, 7);
		}
		board.board[4][4]= new King (4, 4, 1);
		board.board[6][5] = new Knight (6, 5, 2);
		assertFalse("test on knightCheckerHelperTest",(board.isCheckedByKnight(board.getChessByPos(4, 4)).size())==0);
		board.removePieces(6, 5);
		board.board[2][5] = new Knight (2, 5, 2);
		assertFalse("test on knightCheckerHelperTest",(board.isCheckedByKnight(board.getChessByPos(4, 4)).size())==0);
		board.removePieces(2, 5);
		board.board[6][3] = new Knight (6, 3, 2);
		assertFalse("test on knightCheckerHelperTest",(board.isCheckedByKnight(board.getChessByPos(4, 4)).size())==0);
		board.removePieces(6, 3);
		board.board[5][6] = new Knight (5, 6, 2);
		assertFalse("test on knightCheckerHelperTest",(board.isCheckedByKnight(board.getChessByPos(4, 4)).size())==0);
		board.removePieces(5, 6);
		board.board[3][6] = new Knight (3, 6, 2);
		assertFalse("test on knightCheckerHelperTest",(board.isCheckedByKnight(board.getChessByPos(4, 4)).size())==0);
		board.removePieces(3, 6);
		board.board[5][2] = new Knight (5, 2, 2);
		assertFalse("test on knightCheckerHelperTest",(board.isCheckedByKnight(board.getChessByPos(4, 4)).size())==0);
		board.removePieces(5, 2);
		board.board[3][2] = new Knight (3, 2, 2);
		assertFalse("test on knightCheckerHelperTest",(board.isCheckedByKnight(board.getChessByPos(4, 4)).size())==0);
		board.removePieces(3, 2);
	}
	
	/**
	 * test on pawnChecker
	 */
	@Test
	public void pawnCheckerHelperTest() {
		Board board = new Board();
		board.removePieces(1,3);
		board.removePieces(5,3);
		board.removePieces(6,4);
		board.removePieces(2,4);
		//remove everything except the king
		for (int i = 0; i < 8; i++) {
			board.removePieces(i, 6);
		}
		for (int i = 0; i < 8; i++) {
			if(i == 4) {
				continue;
			}
			board.removePieces(i, 7);
		}
		board.board[4][4] = new King (4, 4, 2);
		board.board[3][3] = new Pawn (3, 3, 1);
		assertFalse("test on pawnCheckerHelperTest",(board.isCheckedByPawn(board.getChessByPos(4, 4)).size())==0);
		board.removePieces(3, 3);
		board.board[5][3] = new Pawn (5, 3, 1);
		assertFalse("test on pawnCheckerHelperTest",(board.isCheckedByPawn(board.getChessByPos(4, 4)).size())==0);
		board.removePieces(5, 3);
		board.board[4][4] = new King (4, 4, 1);
		board.board[3][5] = new Pawn (3, 5, 2);
		assertFalse("test on pawnCheckerHelperTest",(board.isCheckedByPawn(board.getChessByPos(4, 4)).size())==0);
		board.removePieces(3, 5);
		board.board[5][5] = new Pawn (5, 5, 2);
		assertFalse("test on pawnCheckerHelperTest",(board.isCheckedByPawn(board.getChessByPos(4, 4)).size())==0);
		board.removePieces(5, 5);
	}
	
	/**
	 * basic test on isCheckmate 
	 */
	@Test
	public void basicCheckmateTest(){
		Board board = new Board();
		//remove everything except the king
		for (int i = 0; i < 8; i++) {
			board.removePieces(i, 6);
		}
		for (int i = 0; i < 8; i++) {
			if(i == 4) {
				continue;
			}
			board.removePieces(i, 7);
		}
		board.board[3][6] = new Pawn(3, 6, 1);
		assertFalse("King can move from checkmate", board.isCheckmate(2));
		board.removePieces(3, 6);
		board.board[2][7] = new Rook(2, 7, 1);
		board.board[2][6] = new Rook(2, 6, 1);
		assertTrue("Checkmate when king move cannot escape with only one checker", board.isCheckmate(2));
		board.board[2][5] = new Queen(2, 5, 1);
		assertTrue("Checkmate when king move cannot escape and there are two checkers", board.isCheckmate(2));
		board.removePieces(2, 7);
		board.removePieces(2, 5);
		board.removePieces(2, 6);
		board.board[2][6] = new Knight(2, 6, 1);
		board.board[3][4] = new Rook (3, 4, 1);
		board.board[5][4] = new Rook (5, 4, 1);
		board.board[6][6] = new Queen (6, 6, 1);
		board.board[4][5] = new Knight(4, 5, 2);
		assertFalse("Only one checker and it can be captured", board.isCheckmate(2));
		board.removePieces(2, 6);
		board.removePieces(3, 4);
		board.removePieces(5, 4);
		board.removePieces(6, 6);
		assertFalse("No checker", board.isCheckmate(2));
	}

}
