package main;

/**
 * 
 * @author kaichenle
 *
 */
public class Pawn extends Pieces {
	boolean firstMove = true;
	/**
	 * constructor
	 * @param x xCoord
	 * @param y yCoord
	 * @param player player 1 or 2
	 */
	public Pawn(int x, int y, int player) {
		super(x, y, player);
		this.firstMove = true;
		this.type = "Pawn";
	}
	/**
	 * see if the chess move against convention without consider other chesses
	 */
	public boolean isValidMove(int newX, int newY){
		if (outOfBoundryOrNotMove(newX, newY)) {
			return false;
		};
		if(this.player == 1) {
			if(Math.abs(newX - x) == 1 && newY - y == 1) {
				return true;
			}
			if(newY - y == 2 && newX == x && firstMove) {
				return true;
			}
			if(newY - y == 1 && newX == x) {
				return true;
			}
			return false;	
		}
		if(this.player == 2) {
			if(Math.abs(newX - x) == 1 && newY - y == -1) {
				return true;
			}
			if(newY - y == -2 && newX == x && firstMove) {
				return true;
			}
			if(newY - y == -1 && newX == x) {
				return true;
			}
			return false;	
		}
		return false;
	}
}
