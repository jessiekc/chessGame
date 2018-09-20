package main;
import java.util.*;

/**
 * 
 * @author kaichenle
 *
 */
public class Rook extends Pieces {
	/**
	 * constructor
	 * @param x xCoord
	 * @param y yCoord
	 * @param player player 1 or 2
	 */
	public Rook(int x, int y, int player) {
		super(x, y, player);
		this.type = "Rook";
	}
	/**
	 * see if the chess move against convention without consider other chesses
	 */
	public boolean isValidMove(int newX, int newY){
		if (outOfBoundryOrNotMove(newX, newY)) {
			return false;
		}
		if( newX != this.x && newY != this.y) {//check movement not vertical or horizontal
			return false;
		}
		return true;
	}
	
}
