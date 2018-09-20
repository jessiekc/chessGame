package main;
import java.util.*;

public class Rook extends Pieces {
	public Rook(int x, int y, int player) {
		super(x, y, player);
		this.type = "Rook";
	}
	//see if the chess move against convention without consider other chesses
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
