package main;

public class Knight extends Pieces {
	public Knight(int x, int y, int player) {
		super(x, y, player);
		this.type = "Knight";
	}
	//see if the chess move against convention without consider other chesses
	public boolean isValidMove(int newX, int newY){
		if (outOfBoundryOrNotMove(newX, newY)) {
			return false;
		}
		if(Math.abs(this.x - newX) == 1 && Math.abs(this.y - newY) == 2) {
			return true;
		}
		if(Math.abs(this.x - newX) == 2 && Math.abs(this.y - newY) == 1) {
			return true;
		}
		return false;
	}
}