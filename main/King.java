package main;

public class King extends Pieces{
	public King(int x, int y, int player) {
		super(x, y, player);
		this.type = "King";
	}
	//see if the chess move against convention without consider other chesses
	public boolean  isValidMove(int newX, int newY) {
		if (outOfBoundryOrNotMove(newX, newY)) {
			return false;
		}
		if (Math.abs(this.x-newX) > 1 || Math.abs(this.y-newY) > 1) {
			return false;
		}
		return true;
	}
}
