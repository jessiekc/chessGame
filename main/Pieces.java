package main;
//Pieces is a parent class. King, Pawn, Queen etc. are different kind of chesses(subclass)
public abstract class Pieces {
	int x = -1;
	int y = -1;
	String id = "";
	int player = -1;
	String type = "";
	
	
	public Pieces(int x, int y, int player) {
		this.x = x;
		this.y = y;
		this.player = player;
	}
	//a helper function check if the chess try to move out of bound or not move
	public boolean outOfBoundryOrNotMove(int x, int y) {
		if( x < 0 || x > 8 || y < 0 || y > 8 || (x == this.x && y == this.y)) {
			return true;
		}
		return false;
	}
	public abstract boolean  isValidMove(int newX, int newY);
	
	
}
