package main;


/**
 * 
 * @author kaichenle
 *
 */
public class Rook extends Pieces {
	/**
	 * constructor
	 * @param x xCoord
	 * @param y yCoord
	 * @param player player 1 or 2
	 */
	public Rook(int x, int y, int player) {
		super(x, y, player);
		this.type = "Rook";
	}
	/**
	 * see if the chess move against convention without consider other chesses
	 */
	public boolean isValidMove(int newX, int newY){
		if (outOfBoundryOrNotMove(newX, newY)) {
			return false;
		}
		if( newX != this.x && newY != this.y) {//check movement not vertical or horizontal
			return false;
		}
		return true;
	}

	/**
	 * Helper functions check if it is a valid path to move Rook.
	 * It will be called inside moveChess with isValidMove in each pieces subclass.
	 * This will check if there are pieces on the board that will influence the fact if it is valid to move chess
	 * @param newX end yCoord
	 * @param newY end yCoord
	 * @param board the chess play on
	 * @return true if valid otherwise false
	 */
	public boolean validMove(int newX, int newY, Board board){
		if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
			return false;
		}
		if ( newX < x) {
			for (int i = x-1; i > newX; i--) {
				if (board.getChessByPos(i, y)!=null){
					return false;
				}
			}
		}
		else if (newX > x) {
			for (int i = x + 1; i < newX; i++) {
				if (board.getChessByPos(i, y)!=null){
					return false;
				}
			}
		}
		else if ( newY < y) {
			for(int i = y-1; i > newY; i--) {
				if(board.getChessByPos(x, i)!=null) {
					return false;
				}
			}
		}
		else if (newY > y) {
			for(int i = y+1; i< newY; i++) {
				if(board.getChessByPos(x, i)!=null) {
					return false;
				}
			}
		}
		if (board.samePlayer(x, y, newX, newY)) {
			return false;
		}
		return true;
	}

	/**
	 * if any pieces could stop input rook or queen(horizontal or vertical) piece from x,y to newX newY
	 * @param newX end xCoord
	 * @param newY end yCoord
	 * @param board that the chess play on
	 * @return true if there is a piece exists that can stop the chess otherwise false
	 */
	public  boolean couldBeStopped(int newX, int newY, Board board){
		if(x < newX) {
			for(int i = x + 1; i < newX; i++) {
				if(board.isChecked(board.getChessByPos(i, y)) > 0)return true;
			}
		}
		else if (x > newX) {
			for(int i = x - 1; i > newX; i--) {
				if(board.isChecked(board.getChessByPos(i, y)) > 0)return true;
			}
		}
		else if (y < newY) {
			for(int i = y + 1; i < newY; i++) {
				if(board.isChecked(board.getChessByPos(x, i)) > 0)return true;
			}
		}
		else if (y > newY) {
			for(int i = y - 1; i > newY; i--) {
				if(board.isChecked(board.getChessByPos(x, i)) > 0)return true;
			}
		}
		return false;
	}

	/**
	 * helper functions check movability of rook or queen(horizontal or vertical)
	 * @param board the game will be on
	 * @return true if the input can move otherwise false
	 */
	public boolean isMovable( Board board) {
		int currX = this.x;
		int currY = this.y;
		//only need to check the closest point
		if(this.validMove( currX + 1, currY, board)||
				this.validMove(currX - 1, currY, board)||
				this.validMove(currX, currY + 1, board)||
				this.validMove(currX, currY - 1,board)) {
			return true;
		}
		return false;
	}
}
