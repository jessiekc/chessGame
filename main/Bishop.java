package main;

public class Bishop extends Pieces {
	public Bishop(int x, int y, int player) {
		super(x, y, player);
		this.type = "Bishop";
	}
	//see if the chess move against convention without consider other chesses
	public boolean isValidMove(int newX, int newY){
		if (outOfBoundryOrNotMove(newX, newY)) {
			return false;
		}
		if(Math.abs(x-newX)!=Math.abs(y-newY)) {
			return false;
		}
		return true;
	}
}
