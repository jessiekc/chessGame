package tests;
import main.*;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author kaichenle
 *
 */
public class StalemateTests {
	/**
	 * test on movableBishopOrQueen
	 */
	@Test
	public void movableBishopOrQueenTests(){
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
		board.board[6][7] = new Bishop(6, 7, 2);
		assertTrue("movableBishopOrQueen", board.movableBishopOrQueen(board.getChessByPos(6, 7)));
		board.board[5][6] = new Pawn(5, 6, 2);
		board.board[7][6] = new Pawn(7, 6, 2);
		assertFalse("movableBishopOrQueen", board.movableBishopOrQueen(board.getChessByPos(6, 7)));
	}
	
	/**
	 * test on movableRookOrQueen
	 */
	@Test
	public void movableRookOrQueenTests(){
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
		board.board[7][7] = new Rook(7, 7, 2);
		assertTrue("movableRookOrQueen", board.movableRookOrQueen(board.getChessByPos(7, 7)));
		board.board[6][7] = new Pawn(6, 7, 2);
		board.board[7][6]= new Pawn(7, 6, 2);
		assertFalse("movableRookOrQueen", board.movableRookOrQueen(board.getChessByPos(7, 7)));
		
	}
	
	/**
	 * test on movableKnight
	 */
	@Test
	public void movableKinghtTests(){
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
		board.board[7][7] = new Knight(7, 7, 2);
		assertTrue("movableKnight", board.movableKnight(board.getChessByPos(7, 7)));
		board.board[5][6] = new Pawn(5, 6, 2);
		board.board[6][5]= new Pawn(6, 5, 2);
		assertFalse("movableKnight", board.movableKnight(board.getChessByPos(7, 7)));
	}
	
	/**
	 * test on movablePawn
	 */
	@Test
	public void movablePawnTests(){
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
		board.board[7][7] = new Pawn(7, 7, 2);
		assertTrue("movablePawn", board.movablePawn(board.getChessByPos(7, 7)));
		board.board[7][6] = new Pawn(7, 6, 2);
		assertFalse("movablePawn", board.movablePawn(board.getChessByPos(7, 7)));
		board.board[3][3] = new Pawn(3, 3, 1);
		assertTrue("movablePawn", board.movablePawn(board.getChessByPos(3, 3)));
		board.board[3][4] = new Pawn(3, 4, 1);
		assertTrue("movablePawn", board.movablePawn(board.getChessByPos(3, 4)));
	}
	
	/**
	 * basic test on isStalemate
	 */
	@Test
	public void basicStalemateTest(){
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
		board.board[2][4] = new Bishop(2, 4, 1);
		board.board[3][4] = new Rook(3, 4, 1);
		board.board[2][6] = new Rook(2, 6, 1);
		assertTrue("Stalemate when king can go nowhere", board.isStalemate(2));
		board.board[4][5] = new Pawn(4, 5, 2);
		assertFalse("A chess otherthen king can go", board.isStalemate(2));
		board.removePieces(2, 6);
		assertFalse("King is not in stalemate", board.isStalemate(2));
	} 
	
	/**
	 * test on example from wiki
	 */
	@Test
	public void wikiStalemateTest(){
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
		board.board[4][6] = new Pawn(4, 6, 1);
		board.board[4][5] = new Queen(4, 5, 1);
		assertTrue("example from wiki", board.isStalemate(2));
	}

}
