package main;
/**
 * 
 * @author kaichenle
 *
 */
public class Knight extends Pieces {
	/**
	 * constructor
	 * @param x xCoord
	 * @param y yCoord
	 * @param player player 1 or 2
	 */
	public Knight(int x, int y, int player) {
		super(x, y, player);
		this.type = "Knight";
	}
	/**
	 * see if the chess move against convention without consider other chesses
	 */
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

	/**
	 * Helper functions check if it is a valid path to move knight.
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
		if (board.samePlayer(x, y, newX, newY)) {
			return false;
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
	 * helper functions check movability of knight
	 * @param board the game will play on
	 * @return true if the input can move otherwise false
	 */
	public boolean isMovable(Board board){
		int currX = this.x;
		int currY = this.y;
		if(this.validMove(currX + 1, currY + 2, board)||
				this.validMove(currX - 1, currY + 2, board)||
				this.validMove(currX + 1, currY - 2, board)||
				this.validMove(currX - 1, currY - 2, board)||
				this.validMove(currX + 2, currY + 1, board)||
				this.validMove(currX - 2, currY + 1, board)||
				this.validMove(currX + 2, currY - 1, board)||
				this.validMove(currX - 2, currY - 1, board)) {
			return true;
		}
		return false;
	}

}