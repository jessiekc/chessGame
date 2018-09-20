package main;
/**
 * Pieces is a parent class. King, Pawn, Queen etc. are different kind of chesses(subclass)
 * @author kaichenle
 *
 */
public abstract class Pieces {
	int x = -1;
	int y = -1;
	String id = "";
	int player = -1;
	String type = "";
	
	/**
	 * Constructor of the super class
	 * @param x
	 * @param y
	 * @param player
	 */
	public Pieces(int x, int y, int player) {
		this.x = x;
		this.y = y;
		this.player = player;
	}
	
	/**
	 * a helper function check if the chess try to move out of bound or not move
	 * @param x
	 * @param y
	 * @return return true if it is not out of boundry and actually 
	 * change x or y or both
	 * return false otherwise
	 */
	public boolean outOfBoundryOrNotMove(int x, int y) {
		if( x < 0 || x > 8 || y < 0 || y > 8 || (x == this.x && y == this.y)) {
			return true;
		}
		return false;
	}
	/**
	 * abstract function implemented in each subclass that
	 * check if it is valid for the pieces to move
	 * (without consider the other pieces)
	 * @param newX x Coord
	 * @param newY y Coord
	 * @return true when it is valid to move by convention
	 */
	public abstract boolean  isValidMove(int newX, int newY);
	
	
}
