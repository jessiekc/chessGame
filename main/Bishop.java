package main;

/**
 * 
 * @author kaichenle
 *
 */
public class Bishop extends Pieces {
	/**
	 * constructor
	 * @param x xCoord
	 * @param y yCoord
	 * @param player player 1 or 2
	 */
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

	/**
	 * Helper functions check if it is a valid path to move Bishop.
	 * It will be called inside moveChess with isValidMove in each pieces subclass.
	 * This will check if there are pieces on the board that will influence the fact if it is valid to move chess
	 * @param newX end yCoord
	 * @param newY end yCoord
	 * @param board the game will play on
	 * @return true if valid otherwise false
	 */
	public boolean validMove(int newX, int newY, Board board){
		if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
			return false;
		}
		if(x < newX && y > newY) {
			for(int i = 1; i< Math.abs(x-newX); i++) {
				if(board.getChessByPos(x+i, y-i)!=null) {
					return false;
				}
			}
		}
		if(x > newX && y > newY) {
			for(int i = 1; i< Math.abs(x-newX); i++) {
				if(board.getChessByPos(x-i, y-i)!=null) {
					return false;
				}
			}
		}
		if(x < newX && y < newY) {
			for(int i = 1; i< Math.abs(x-newX); i++) {
				if(board.getChessByPos(x+i, y+i)!=null) {
					return false;
				}
			}
		}
		if(x > newX && y < newY) {
			for(int i = 1; i< Math.abs(x-newX); i++) {
				if(board.getChessByPos(x-i, y+i)!=null) {
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
	 * if any pieces could stop input bishop or queen(diaganol) piece from x,y to newX newY
	 * @param newX end xCoord
	 * @param newY end yCoord
	 * @param board the game will play on
	 * @return true if there is a piece exists otherwise false
	 */
	public  boolean couldBeStopped(int newX, int newY, Board board){
		if(x < newX && y < newY) {
			for (int i = 1; i < Math.abs(x - newX); i++) {
				board.moveChess(x, y, x+i, y+i);
				if(board.isChecked(board.getChessByPos(x, y)) > 0) {
					board.moveChess(x, y, x-i, y-i);
					return true;
				}
				board.moveChess(x, y, x-i, y-i);

			}
		}
		else if(x > newX && y < newY) {
			for (int i = 1; i < Math.abs(x - newX); i++) {
				board.moveChess(x, y, x-i, y+i);
				if(board.isChecked(board.getChessByPos(x , y )) > 0) {
					board.moveChess(x, y, x+i, y-i);
					return true;
				}
				board.moveChess(x, y, x+i, y-i);
			}
		}
		else if(x < newX && y > newY) {
			for (int i = 1; i < Math.abs(x - newX); i++) {
				board.moveChess(x, y, x+i, y-i);
				if(board.isChecked(board.getChessByPos(x , y)) > 0) {
					board.moveChess(x, y, x-i, y+i);
					return true;
				}
				board.moveChess(x, y, x-i, y+i);
			}
		}
		else if(x > newX && y > newY) {
			for (int i = 1; i < Math.abs(x - newX); i++) {
				board.moveChess(x, y, x-i, y-i);
				if(board.isChecked(board.getChessByPos(x , y )) > 0) {
					board.moveChess(x, y, x+i, y+i);
					return true;
				}
				board.moveChess(x, y, x+i, y+i);
			}
		}
		return false;
	}
	/**
	 * helper functions check movability of bishop or queen(diaganol)
	 * @param board the game will be on
	 * @return true if the input can move otherwise false
	 */
	public boolean isMovable(Board board){
		int currX = this.x;
		int currY = this.y;
		if(this.validMove(currX + 1, currY + 1, board)||
				this.validMove( currX - 1, currY + 1, board)||
				this.validMove( currX + 1, currY - 1, board)||
				this.validMove(currX - 1, currY - 1, board)) {
			return true;
		}
		return false;
	}
}
