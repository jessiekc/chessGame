package main;

public class Queen extends Pieces {
	public Queen(int x, int y, int player) {
		super(x, y, player);
		this.type = "Queen";
	}
	//see if the chess move against convention without consider other chesses
	public boolean isValidMove(int newX, int newY){
		if (outOfBoundryOrNotMove(newX, newY)) {
			return false;
		}
		if( newX != this.x && newY != this.y && Math.abs(x-newX)!=Math.abs(y-newY)) {
			return false;
		}
		return true;
	}
}
