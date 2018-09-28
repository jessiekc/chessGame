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


	/**
	 * Helper functions check if it is a valid path to move Pawn.
	 * It will be called inside moveChess with isValidMove in each pieces subclass.
	 * This will check if there are pieces on the board that will influence the fact if it is valid to move chess
	 * @param newX end yCoord
	 * @param newY end yCoord
	 * @param board the chess will play on
	 * @return true if valid otherwise false
	 */
	public boolean validMove(int newX, int newY, Board board){
		if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
			return false;
		}
		if(x == newX) {
			if(board.getChessByPos(newX, newY)!=null) {
				return false;
			}
			if(Math.abs(newY-y)==2) {
				if(board.getChessByPos(newX, (newY+y)/2)!=null) {
					return false;
				}
			}
		}
		else {
			if (board.samePlayer(x, y, newX, newY)) {
				return false;
			}
			if (board.getChessByPos(newX, newY) == null) {//go diagnose must capture something
				return false;
			}
			return true;
		}
		return true;
	}
	/**
	 * if any pieces could stop input bishop or queen(diaganol) piece from x,y to newX newY
	 * @param newX end xCoord
	 * @param newY end yCoord
	 * @param board the game will play on
	 * @return true if there is a piece exists otherwise false
	 */
	public  boolean couldBeStopped(int newX, int newY, Board board){
		return false;//cannot be blocked
	}


	/**
	 * helper functions check movability of pawn
	 * @param board the game will be on
	 * @return true if the input can move otherwise false
	 */
	public  boolean isMovable(Board board){
		int currX = this.x;
		int currY = this.y;
		if(this.player == 1) {
			if(this.validMove(currX + 1, currY + 1, board)||
					this.validMove(currX, currY + 1, board)||
					this.validMove(currX - 1, currY + 1, board)) {
				return true;
			}
		}
		else if(this.player == 2){
			if(this.validMove(currX + 1, currY - 1, board)||
					this.validMove(currX, currY - 1, board)||
					this.validMove(currX - 1, currY - 1, board)) {
				return true;
			}
		}
		return false;
	}
}
