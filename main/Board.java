package main;

/**
 * 
 * @author kaichenle
 *
 */
public class Board {
	public Pieces[][] board = new Pieces[8][8];
	/*
	 * a constructor that will create a standard 8*8 chess board with chesses on it
	 */
	public Board() {
		for (int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j]=null;
			}
		}
		for (int i = 0; i < 8; i++) {
			board[i][1] = new Pawn(i, 1, 1);
			board[i][6] = new Pawn(i, 6, 2);
		}
		board[0][0] = new Rook(0, 0, 1);
		board[7][0] = new Rook(7, 0, 1);
		board[0][7] = new Rook(0, 7, 2);
		board[7][7] = new Rook(7, 7, 2);
		
		board[1][0] = new Knight(1, 0, 1);
		board[6][0] = new Knight(6, 0, 1);
		board[1][7] = new Knight(1, 7, 2);
		board[6][7] = new Knight(6, 7, 2);
		
		board[2][0] = new Bishop(2, 0, 1);
		board[5][0] = new Bishop(5, 0,  1);
		board[2][7] = new Bishop(2, 7, 2);
		board[5][7] = new Bishop(5, 7, 2);
		
		board[3][0] = new Queen(3, 0, 1);
		board[3][7] = new Queen(3, 7, 2);
		board[4][0] = new King(4, 0, 1);
		board[4][7] = new King(4, 7, 2);
	}
	
	/**
	 * Get the chess by its x y
	 * @param x xCoord
	 * @param y yCoord
	 * @return the chess piece in the grid of (x,y)
	 */
	public Pieces getChessByPos(int x, int y) {
		if(x < 0 || x >= 8 || y < 0 || y >= 8) {
			return null;
		}
		return board[x][y];
	}
	
	/**
	 * chess if (x, y) and (newX, newY) belongs to the same player
	 * @param x Piece1 xCoord
	 * @param y Piece1 yCoord
	 * @param newX Piece2 xCoord
	 * @param newY Piece2 yCoord
	 * @return
	 */
	public boolean samePlayer(int x, int y, int newX, int newY) {
		if(this.getChessByPos(newX, newY)==null)return false;
		if(this.getChessByPos(newX, newY).player==this.getChessByPos(x, y).player) {
			return true;
		}
		return false;
	}
	
	/**
	 * remove the chess piece in (x, y)
	 * @param x xCoord
	 * @param y yCoord
	 */
	public void removePieces(int x, int y) {
		board[x][y] = null;
	}
	
	//
	//helper function in general
	//
	
	/**
	 * Helper functions check if it is a valid path to move king.
	 * It will be called inside moveChess with isValidMove in each pieces subclass.
	 * This will check if there are pieces on the board that will influence the fact if it is valid to move chess
	 * @param x start xCoord
	 * @param y start yCoord
	 * @param newX end yCoord
	 * @param newY end yCoord
	 * @return true if valid otherwise false
	 */
	public boolean validMoveKing(int x, int y, int newX, int newY) {
		if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
			return false;
		}
		if (this.samePlayer(x, y, newX, newY)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Helper functions check if it is a valid path to move Rook.
	 * It will be called inside moveChess with isValidMove in each pieces subclass.
	 * This will check if there are pieces on the board that will influence the fact if it is valid to move chess
	 * @param x start xCoord
	 * @param y start yCoord
	 * @param newX end yCoord
	 * @param newY end yCoord
	 * @return true if valid otherwise false
	 */
	public boolean validMoveRook(int x, int y, int newX, int newY) {
		if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
			return false;
		}
		
		if ( newX < x) {
			for (int i = x-1; i > newX; i--) {
				if (this.getChessByPos(i, y)!=null){
					return false;
				}
			}
		}
		else if (newX > x) {
			for (int i = x + 1; i < newX; i++) {
				if (this.getChessByPos(i, y)!=null){
					return false;
				}
			}
		}
		else if ( newY < y) {
			for(int i = y-1; i > newY; i--) {
				if(this.getChessByPos(x, i)!=null) {
					return false;
				}
			}
		}
		else if (newY > y) {
			for(int i = y+1; i< newY; i++) {
				if(this.getChessByPos(x, i)!=null) {
					return false;
				}
			}
		}
		if (this.samePlayer(x, y, newX, newY)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Helper functions check if it is a valid path to move Bishop.
	 * It will be called inside moveChess with isValidMove in each pieces subclass.
	 * This will check if there are pieces on the board that will influence the fact if it is valid to move chess
	 * @param x start xCoord
	 * @param y start yCoord
	 * @param newX end yCoord
	 * @param newY end yCoord
	 * @return true if valid otherwise false
	 */
	public boolean validMoveBishop(int x, int y, int newX, int newY) {
		if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
			return false;
		}
		if(x < newX && y > newY) {
			for(int i = 1; i< Math.abs(x-newX); i++) {
				if(this.getChessByPos(x+i, y-i)!=null) {
					return false;
				}
			}
		}
		if(x > newX && y > newY) {
			for(int i = 1; i< Math.abs(x-newX); i++) {
				if(this.getChessByPos(x-i, y-i)!=null) {
					return false;
				}
			}
		}
		if(x < newX && y < newY) {
			for(int i = 1; i< Math.abs(x-newX); i++) {
				if(this.getChessByPos(x+i, y+i)!=null) {
					return false;
				}
			}
		}
		if(x > newX && y < newY) {
			for(int i = 1; i< Math.abs(x-newX); i++) {
				if(this.getChessByPos(x-i, y+i)!=null) {
					return false;
				}
			}
		}
		if (this.samePlayer(x, y, newX, newY)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Helper functions check if it is a valid path to move Queen.
	 * It will be called inside moveChess with isValidMove in each pieces subclass.
	 * This will check if there are pieces on the board that will influence the fact if it is valid to move chess
	 * @param x start xCoord
	 * @param y start yCoord
	 * @param newX end yCoord
	 * @param newY end yCoord
	 * @return true if valid otherwise false
	 */
	public boolean validMoveQueen(int x, int y, int newX, int newY) {
		if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
			return false;
		}
		if(validMoveBishop(x,y,newX,newY)||validMoveRook(x, y, newX, newY)) {
			return true;
		}
		if (this.samePlayer(x, y, newX, newY)) {
			return false;
		}
		return false;
	}
	
	/**
	 * Helper functions check if it is a valid path to move knight.
	 * It will be called inside moveChess with isValidMove in each pieces subclass.
	 * This will check if there are pieces on the board that will influence the fact if it is valid to move chess
	 * @param x start xCoord
	 * @param y start yCoord
	 * @param newX end yCoord
	 * @param newY end yCoord
	 * @return true if valid otherwise false
	 */
	public boolean validMoveKnight(int x, int y, int newX, int newY) {
		if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
			return false;
		}
		if (this.samePlayer(x, y, newX, newY)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Helper functions check if it is a valid path to move Pawn.
	 * It will be called inside moveChess with isValidMove in each pieces subclass.
	 * This will check if there are pieces on the board that will influence the fact if it is valid to move chess
	 * @param x start xCoord
	 * @param y start yCoord
	 * @param newX end yCoord
	 * @param newY end yCoord
	 * @return true if valid otherwise false
	 */
	public boolean validMovePawn(int x, int y, int newX, int newY) {
		if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
			return false;
		}
		if(x == newX) {
			if(this.getChessByPos(newX, newY)!=null) {
				return false;
			}
			if(Math.abs(newY-y)==2) {
				if(this.getChessByPos(newX, (newY+y)/2)!=null) {
					return false;
				}
			}
		}
		else {
			if (this.samePlayer(x, y, newX, newY)) {
				return false;
			}
			if (this.getChessByPos(newX, newY) == null) {//go diagnose must capture something
				return false;
			}
			return true;
		}
		return true;
	}
	
	/**
	 * function move pieces from (x, y) and (newX, new)
	 * it will check if it is valid before moving the piece
	 * if the movement was succeed it will return true
	 * @param x start xCoord
	 * @param y start yCoord
	 * @param newX end yCoord
	 * @param newY end yCoord
	 * @return true if moved successfully otherwise false
	 */
	public boolean moveChess(int x, int y, int newX, int newY) {
		if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
			return false;
		}
		if(this.getChessByPos(x, y) == null) {
			return false;
		}
		if(this.getChessByPos(x, y).isValidMove(newX, newY) == false) {//against rule
			return false;
		}
		
		if(this.getChessByPos(x, y).type == "King") {
			if(this.validMoveKing(x, y, newX, newY) == false) {	
				
				return false;
			}
		}
		else if(this.getChessByPos(x, y).type == "Rook") {
			if(this.validMoveRook(x, y, newX, newY) == false) {
				return false;
			}
		}
		else if(this.getChessByPos(x, y).type == "Bishop") {
			if(this.validMoveBishop(x, y, newX, newY) == false) {
				return false;
			}
		}
		else if(this.getChessByPos(x, y).type == "Queen") {
			if(this.validMoveQueen(x, y, newX, newY) == false) {
				return false;
			}
		}
		else if(this.getChessByPos(x, y).type == "Knight") {
			if(this.validMoveKnight(x, y, newX, newY) == false) {
				return false;
			}
		}
		else if(this.getChessByPos(x, y).type == "Pawn") {
			if(this.validMovePawn(x, y, newX, newY) == false) {
				return false;
			}
			((Pawn)this.getChessByPos(x, y)).firstMove = false;
		}
		board[newX][newY]=board[x][y];
		board[newX][newY].x=newX;
		board[newX][newY].y=newY;
		board[x][y]=null;
		return true;
	}
	
	//
	//checkmate and its helper functions
	//
	
	/**
	 * get king of input player
	 * @param player player number 1 or 2
	 * @return the king piece belong to player 1 or 2
	 */
	public Pieces getKing(int player) {
		for(int i = 0; i < 8; i++ ) {
			for(int j = 0; j < 8; j++) {
				Pieces currPiece = this.getChessByPos(i, j);
				if(currPiece == null) {
					continue;
				}
				else {
					if(currPiece instanceof King && currPiece.player == player) {
						return currPiece;
					}
				}
			}
		}
		return null;
	}
	
	
	/**
	 * helper functions check if the chess is checked by rook or queen(vertical and horizontal)
	 * @param checkingPiece the piece be checked
	 * @return how many checkers available
	 */
	public int isCheckedByRookOrQueen(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;
		int count = 0;
		for(int i = x + 1; i < 8; i++) {
			if(this.getChessByPos(i, y)!=null) {
				if(this.getChessByPos(i, y).player == checkingPiece.player) {
					break;
				}
				else if(this.getChessByPos(i, y) instanceof Queen || this.getChessByPos(i, y) instanceof Rook){
					count++;
					break;
				}
				else {
					break;
				}
			}
		}
		for(int i = x - 1; i >= 0; i--) {
			if(this.getChessByPos(i, y)!=null) {
				if(this.getChessByPos(i, y).player == checkingPiece.player) {
					break;
				}
				else if(this.getChessByPos(i, y) instanceof Queen || this.getChessByPos(i, y) instanceof Rook){
					count++;
					break;
				}
				else {
					break;
				}
			}
		}
		for(int i = y + 1; i < 8; i++) {
			if(this.getChessByPos(x, i)!=null) {
				if(this.getChessByPos(x, i).player == checkingPiece.player) {
					break;
				}
				else if(this.getChessByPos(x, i) instanceof Queen || this.getChessByPos(x, i) instanceof Rook){
					count++;
					break;
				}
				else {
					break;
				}
			}
		}
		for(int i = y - 1; i >= 0; i--) {
			if(this.getChessByPos(x, i)!=null) {
				if(this.getChessByPos(x, i).player == checkingPiece.player) {
					break;
				}
				else if(this.getChessByPos(x, i) instanceof Queen || this.getChessByPos(x, i) instanceof Rook){
					count++;
					break;
				}
				else {
					break;
				}
			}
		}
		return count;
	}
	
	/**
	 * helper functions check if the chess is checked by bishop or queen(diaganol)
	 * @param checkingPiece the piece be checked
	 * @return how many checkers available
	 */
	public int isCheckedByBishopOrQueen(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;

		int count = 0;
		for(int i = 1; i < 8; i ++) {
			int newX = x + i;
			int newY = y + i;
			if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
				break;
			}
			if(this.getChessByPos(newX, newY)==null) {
				continue;
			}
			else if(this.getChessByPos(newX, newY).player == checkingPiece.player){
				break;
			}
			else if(this.getChessByPos(newX, newY) instanceof Bishop || this.getChessByPos(newX, newY) instanceof Queen) {
				count++;
				break;
			}
			else {
				break;
			}
		}
		for(int i = 1; i < 8; i ++) {
			int newX = x - i;
			int newY = y + i;
			if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
				break;
			}
			if(this.getChessByPos(newX, newY)==null) {
				continue;
			}
			else if(this.getChessByPos(newX, newY).player == checkingPiece.player){
				break;
			}
			else if(this.getChessByPos(newX, newY) instanceof Bishop || this.getChessByPos(newX, newY) instanceof Queen) {
				count++;
				break;
			}
			else {
				break;
			}
		}
		for(int i = 1; i < 8; i ++) {
			int newX = x + i;
			int newY = y - i;
			if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
				break;
			}
			if(this.getChessByPos(newX, newY)==null) {
				continue;
			}
			else if(this.getChessByPos(newX, newY).player == checkingPiece.player){
				break;
			}
			else if(this.getChessByPos(newX, newY) instanceof Bishop || this.getChessByPos(newX, newY) instanceof Queen) {
				count++;
				break;
			}
			else {
				break;
			}
		}
		for(int i = 1; i < 8; i ++) {
			int newX = x - i;
			int newY = y - i;
			if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
				break;
			}
			if(this.getChessByPos(newX, newY)==null) {
				continue;
			}
			else if(this.getChessByPos(newX, newY).player == checkingPiece.player){
				break;
			}
			else if(this.getChessByPos(newX, newY) instanceof Bishop || this.getChessByPos(newX, newY) instanceof Queen) {
				count++;
				break;
			}
			else {
				break;
			}
		}
		return count;
	}
	
	/**
	 * helper functions check if the chess is checked by knight
	 * @param checkingPiece the piece be checked
	 * @return how many checkers available
	 */
	public int isCheckedByKnight(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;

		int count = 0;
		if( x+2 >= 0 && x+2 < 8 && y+1 >=0 && y+1 < 8) {
			Pieces tempPiece = this.getChessByPos(x+2, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					count++;
				}
			}
		}
		if( x-2 >= 0 && x-2 < 8 && y+1 >=0 && y+1 < 8) {
			Pieces tempPiece = this.getChessByPos(x-2, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					count++;
				}
			}
		}
		if( x+2 >= 0 && x+2 < 8 && y-1 >=0 && y-1 < 8) {
			Pieces tempPiece = this.getChessByPos(x+2, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					count++;
				}
			}
		}
		if( x-2 >= 0 && x-2 < 8 && y-1 >=0 && y-1 < 8) {
			Pieces tempPiece = this.getChessByPos(x-2, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					count++;
				}
			}
		}
		if( x+1 >= 0 && x+1 < 8 && y+2 >=0 && y+2 < 8) {
			Pieces tempPiece = this.getChessByPos(x+1, y+2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					count++;
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y+2 >=0 && y+2 < 8) {
			Pieces tempPiece = this.getChessByPos(x-1, y+2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					count++;
				}
			}
		}
		if( x+1 >= 0 && x+1 < 8 && y-2 >=0 && y-2 < 8) {
			Pieces tempPiece = this.getChessByPos(x+1, y-2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					count++;
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y-2 >=0 && y-2 < 8) {
			Pieces tempPiece = this.getChessByPos(x-1, y-2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					count++;
				}
			}
		}
		return count;
	}	
	
	/**
	 * helper functions check if the chess is checked by Pawn
	 * @param checkingPiece the piece be checked
	 * @return how many checkers available
	 */
	public int isCheckedByPawn(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;
		int count = 0;
		if( x+1 >= 0 && x+1 < 8 && y+1 >=0 && y+1 < 8 && checkingPiece.player == 1) {
			Pieces tempPiece = this.getChessByPos(x+1, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					count++;
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y+1 >=0 && y+1 < 8 && checkingPiece.player == 1) {
			Pieces tempPiece = this.getChessByPos(x-1, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					count++;
				}
			}
		}
		if( x+1 >= 0 && x+1 < 8 && y-1 >=0 && y-1 < 8 && checkingPiece.player == 2) {
			Pieces tempPiece = this.getChessByPos(x+1, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					count++;
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y-1 >=0 && y-1 < 8 && checkingPiece.player == 2) {
			Pieces tempPiece = this.getChessByPos(x-1, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * helper function see if the pieces is checked or not
	 * @param king the piece be checked usually the king
	 * @return true if it has one or more checkers otherwise false
	 */
	public boolean isChecked(Pieces king) {
		if(king == null)return false;
		int numRookOrQueen = this.isCheckedByRookOrQueen(king);
		int numBishopOrQueen = this.isCheckedByBishopOrQueen(king);
		int numKnight = this.isCheckedByKnight(king);
		int numPawn = this.isCheckedByPawn(king);
		if(numRookOrQueen + numBishopOrQueen + numKnight + numPawn > 0) return true;
		else return false;
	}
	
	//
	//helper functions work when only one checker exist
	//return the specific chess checker
	//
	/**
	 * helper functions work when only one vertical or horizontal checker exist
	 * @param checkingPiece the piece to be checked
	 * @return the checkers piece that check on the input pieces
	 */
	public Pieces rookOrQueenChecker(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;
		for(int i = x + 1; i < 8; i++) {
			if(this.getChessByPos(i, y)!=null) {
				if(this.getChessByPos(i, y).player == checkingPiece.player) {
					break;
				}
				else if(this.getChessByPos(i, y) instanceof Queen || this.getChessByPos(i, y) instanceof Rook){
					return this.getChessByPos(i, y);
				}
				else {
					break;
				}
			}
		}
		for(int i = x - 1; i >= 0; i--) {
			if(this.getChessByPos(i, y)!=null) {
				if(this.getChessByPos(i, y).player == checkingPiece.player) {
					break;
				}
				else if(this.getChessByPos(i, y) instanceof Queen || this.getChessByPos(i, y) instanceof Rook){
					return this.getChessByPos(i, y);
				}
				else {
					break;
				}
			}
		}
		for(int i = y + 1; i < 8; i++) {
			if(this.getChessByPos(x, i)!=null) {
				if(this.getChessByPos(x, i).player == checkingPiece.player) {
					break;
				}
				else if(this.getChessByPos(x, i) instanceof Queen || this.getChessByPos(x, i) instanceof Rook){
					return this.getChessByPos(x, i);
				}
				else {
					break;
				}
			}
		}
		for(int i = y - 1; i >= 0; i--) {
			if(this.getChessByPos(x, i)!=null) {
				if(this.getChessByPos(x, i).player == checkingPiece.player) {
					break;
				}
				else if(this.getChessByPos(x, i) instanceof Queen || this.getChessByPos(x, i) instanceof Rook){
					return this.getChessByPos(x, i);
				}
				else {
					break;
				}
			}
		}
		return null;
	}
	
	/**
	 * helper functions work when only one diaganol checker exist
	 * @param checkingPiece the piece to be checked
	 * @return the checkers piece that check on the input pieces
	 */
	public Pieces bishopOrQueenChecker(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;
		for(int i = 0; i < 8; i ++) {
			int newX = x + i;
			int newY = y + i;
			if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
				break;
			}
			if(this.getChessByPos(newX, newY)==null) {
				continue;
			}
			else if(this.getChessByPos(newX, newY).player == checkingPiece.player){
				break;
			}
			else if(this.getChessByPos(newX, newY) instanceof Bishop || this.getChessByPos(newX, newY) instanceof Queen) {
				return this.getChessByPos(newX, newY);
			}
			else {
				break;
			}
		}
		for(int i = 0; i < 8; i ++) {
			int newX = x - i;
			int newY = y + i;
			if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
				break;
			}
			if(this.getChessByPos(newX, newY)==null) {
				continue;
			}
			else if(this.getChessByPos(newX, newY).player == checkingPiece.player){
				break;
			}
			else if(this.getChessByPos(newX, newY) instanceof Bishop || this.getChessByPos(newX, newY) instanceof Queen) {
				return this.getChessByPos(newX, newY);
			}
			else {
				break;
			}
		}
		for(int i = 0; i < 8; i ++) {
			int newX = x + i;
			int newY = y - i;
			if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
				break;
			}
			if(this.getChessByPos(newX, newY)==null) {
				continue;
			}
			else if(this.getChessByPos(newX, newY).player == checkingPiece.player){
				break;
			}
			else if(this.getChessByPos(newX, newY) instanceof Bishop || this.getChessByPos(newX, newY) instanceof Queen) {
				return this.getChessByPos(newX, newY);
			}
			else {
				break;
			}
		}
		for(int i = 0; i < 8; i ++) {
			int newX = x - i;
			int newY = y - i;
			if(newX >= 8 || newX < 0 || newY >= 8 || newY < 0) {
				break;
			}
			if(this.getChessByPos(newX, newY)==null) {
				continue;
			}
			else if(this.getChessByPos(newX, newY).player == checkingPiece.player){
				break;
			}
			else if(this.getChessByPos(newX, newY) instanceof Bishop || this.getChessByPos(newX, newY) instanceof Queen) {
				return this.getChessByPos(newX, newY);
			}
			else {
				break;
			}
		}
		return null;
	}
	
	/**
	 * helper functions work when only one Knight checker exist
	 * @param checkingPiece the piece to be checked
	 * @return the checkers piece that check on the input pieces
	 */
	public Pieces knightChecker(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;
		//x:2 y:1
		if( x+2 >= 0 && x+2 < 8 && y+1 >=0 && y+1 < 8) {
			Pieces tempPiece = this.getChessByPos(x+2, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					return tempPiece;
				}
			}
		}
		if( x-2 >= 0 && x-2 < 8 && y+1 >=0 && y+1 < 8) {
			Pieces tempPiece = this.getChessByPos(x-2, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					return tempPiece;
				}
			}
		}
		if( x+2 >= 0 && x+2 < 8 && y-1 >=0 && y-1 < 8) {
			Pieces tempPiece = this.getChessByPos(x+2, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					return tempPiece;
				}
			}
		}
		if( x-2 >= 0 && x-2 < 8 && y-1 >=0 && y-1 < 8) {
			Pieces tempPiece = this.getChessByPos(x-2, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					return tempPiece;
				}
			}
		}
		//x:1 y:2
		if( x+1 >= 0 && x+1 < 8 && y+2 >=0 && y+2 < 8) {
			Pieces tempPiece = this.getChessByPos(x+1, y+2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					return tempPiece;
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y+2 >=0 && y+2 < 8) {
			Pieces tempPiece = this.getChessByPos(x-1, y+2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					return tempPiece;
				}
			}
		}
		if( x+1 >= 0 && x+1 < 8 && y-2 >=0 && y-2 < 8) {
			Pieces tempPiece = this.getChessByPos(x+1, y-2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					return tempPiece;
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y-2 >=0 && y-2 < 8) {
			Pieces tempPiece = this.getChessByPos(x-1, y-2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					return tempPiece;
				}
			}
		}
		return null;
	}	
	
	/**
	 * helper functions work when only one pawn checker exist
	 * @param checkingPiece the piece to be checked
	 * @return the checkers piece that check on the input pieces
	 */
	public Pieces pawnChecker(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;
		if( x+1 >= 0 && x+1 < 8 && y+1 >=0 && y+1 < 8 && checkingPiece.player == 1) {
			Pieces tempPiece = this.getChessByPos(x+1, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					return tempPiece;
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y+1 >=0 && y+1 < 8 && checkingPiece.player == 1) {
			Pieces tempPiece = this.getChessByPos(x-1, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					return tempPiece;
				}
			}
		}
		if( x+1 >= 0 && x+1 < 8 && y-1 >=0 && y-1 < 8 && checkingPiece.player == 2) {
			Pieces tempPiece = this.getChessByPos(x+1, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					return tempPiece;
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y-1 >=0 && y-1 < 8 && checkingPiece.player == 2) {
			Pieces tempPiece = this.getChessByPos(x-1, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					return tempPiece;
				}
			}
		}
		return null;
	}
	
	/**
	 * if any pieces could stop input rook or queen(horizontal or vertical) piece from x,y to newX newY
	 * @param x start xCoord
	 * @param y start yCoord
	 * @param newX end xCoord
	 * @param newY end yCoord
	 * @return true if there is a piece exists otherwise false
	 */
	public boolean couldStopRookOrQueen(int x, int y, int newX, int newY) {
		if(x < newX) {
			for(int i = x + 1; i < newX; i++) {
				if(this.isChecked(this.getChessByPos(i, y)))return true;
			}
		}
		else if (x > newX) {
			for(int i = x - 1; i > newX; i--) {
				if(this.isChecked(this.getChessByPos(i, y)))return true;
			}
		}
		else if (y < newY) {
			for(int i = y + 1; i < newY; i++) {
				if(this.isChecked(this.getChessByPos(x, i)))return true;
			}
		}
		else if (y > newY) {
			for(int i = y - 1; i > newY; i--) {
				if(this.isChecked(this.getChessByPos(x, i)))return true;
			}
		}
		return false;
	}
	
	/**
	 * if any pieces could stop input bishop or queen(diaganol) piece from x,y to newX newY
	 * @param x start xCoord
	 * @param y start yCoord
	 * @param newX end xCoord
	 * @param newY end yCoord
	 * @return true if there is a piece exists otherwise false
	 */
	public boolean couldStopBishopOrQueen(int x, int y, int newX, int newY) {
		if(x < newX && y < newY) {
			for (int i = 1; i < Math.abs(x - newX); i++) {
				this.moveChess(x, y, x+i, y+i);
				if(this.isChecked(this.getChessByPos(x + i, y + i))) {
					this.moveChess(x+i, y+i, x, y);
					return true;
				}
				this.moveChess(x+i, y+i, x, y);
					
			}
		}
		else if(x > newX && y < newY) {
			for (int i = 1; i < Math.abs(x - newX); i++) {
				this.moveChess(x, y, x-i, y+i);
				if(this.isChecked(this.getChessByPos(x - i, y + i))) {
					this.moveChess(x-i, y+i, x, y);
					return true;
				}
				this.moveChess(x-i, y+i, x, y);
			}
		}
		else if(x < newX && y > newY) {
			for (int i = 1; i < Math.abs(x - newX); i++) {
				this.moveChess(x, y, x+i, y-i);
				if(this.isChecked(this.getChessByPos(x + i, y - i))) {
					this.moveChess(x+i, y-i, x, y);
					return true;
				}
				this.moveChess(x+i, y-i, x, y);
			}
		}
		else if(x > newX && y > newY) {
			for (int i = 1; i < Math.abs(x - newX); i++) {
				this.moveChess(x, y, x-i, y-i);
				if(this.isChecked(this.getChessByPos(x - i, y - i))) {
					this.moveChess(x-i, y-i, x, y);
					return true;
				}
				this.moveChess(x-i, y-i, x, y);
			}
		}
		return false;
	}
	
	//
	//Checkmate main function:
	//it will see if the king is checked
	//if it is checked then try to move the king around to escape from being captured
	//if we cannot avoid being checked by moving the king around
	//then check how many checkers there are check on the king
	//if there are two or more than there is no way to stop it in one action
	//if there are only one chess check on the king, 
	//then try to figure out if there is a way to capture the chess
	//block it from getting to the king
	//
	/**
	 * Checkmate main function:
	 * it will see if the king is checked
	 * if it is checked then try to move the king around to escape from being captured
	 * if we cannot avoid being checked by moving the king around
	 * then check how many checkers there are check on the king
	 * if there are two or more than there is no way to stop it in one action
	 * if there are only one chess check on the king, 
	 * then try to figure out if there is a way to capture the chess
	 * block it from getting to the king
	 * 
	 * @param player whose king might be checkmate
	 * @return true if the king of the input player is checkmated otherwise false
	 */
	public boolean isCheckmate(int player) {
		Pieces king = this.getKing(player);
		int oldKingX = king.x;
		int oldKingY = king.y;
		//get num of checker seperately
		int numRookOrQueen = this.isCheckedByRookOrQueen(king);
		int numBishopOrQueen = this.isCheckedByBishopOrQueen(king);
		int numKnight = this.isCheckedByKnight(king);
		int numPawn = this.isCheckedByPawn(king);
		//get num of checker
		int numChecked = numRookOrQueen + numBishopOrQueen + numKnight + numPawn;
		if(numChecked == 0) return false;//since no one check on the king
		
		//if one or more checker, check if move the king will solve the problem
		if(this.moveChess(king.x, king.y, oldKingX - 1 , oldKingY - 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
		}
		if(this.moveChess(king.x, king.y, oldKingX + 1 , oldKingY + 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
		}
		if(this.moveChess(king.x, king.y, oldKingX - 1 , oldKingY + 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
		}
		if(this.moveChess(king.x, king.y, oldKingX + 1 , oldKingY - 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
		}
		if(this.moveChess(king.x, king.y, oldKingX , oldKingY - 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
		}
		if(this.moveChess(king.x, king.y, oldKingX , oldKingY + 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
		}
		if(this.moveChess(king.x, king.y, oldKingX - 1, oldKingY)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				return false;
			}
		}
		if(this.moveChess(king.x, king.y, oldKingX + 1, oldKingY)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
		}
		this.moveChess(king.x, king.y, oldKingX , oldKingY);
		//if king cannot be moved and more than checkers available return true
		if(numChecked > 1) {
			return true;
		}//if king cannot be moved and only one checker available
		else {//we can try to either capture it or block it
			if(numRookOrQueen == 1) {//the checker is Rook or Queen
				Pieces checker = this.rookOrQueenChecker(king);
				//check if the checker could be captured
				if(this.isChecked(checker)) {
					return false;
				}
				//check if the checker could be blocked
				if(this.couldStopRookOrQueen(checker.x, checker.y, king.x, king.y)) {
					return false;
				}
			}
			else if (numBishopOrQueen == 1) {//the checker is bishop or queen
				//check if the checker could be captured
				Pieces checker = this.bishopOrQueenChecker(king);
				if(this.isChecked(checker)) {
					return false;
				}
				//check if the checker could be blocked
				if(this.couldStopBishopOrQueen(checker.x, checker.y, king.x, king.y)) {
					return false;
				}
			}
			else if (numKnight == 1) {//the checker is knight
				//check if the checker could be captured
				if(this.isChecked(this.knightChecker(king))) {
					return false;
				}
				//check if the checker could be blocked
				//knight cannot be blocked
				return true;
			}
			else if (numPawn == 1) {//the checker is pawn
				//check if the checker could be captured
				if(this.isChecked(this.pawnChecker(king))) {
					return false;
				}
				//check if the checker could be blocked
				//pawn cannot be blocked
				return true;
			}
		}
		return true;
	}
	
	//
	//stalemate
	//
	
	/**
	 * helper functions check movability of rook or queen(horizontal or vertical)
	 * @param currPiece the input piece that need to be check if it can move
	 * @return true if the input can move otherwise false
	 */
	public boolean movableRookOrQueen(Pieces currPiece) {
		int currX = currPiece.x;
		int currY = currPiece.y;
		//only need to check the closest point
		if(this.validMoveRook(currX, currY, currX + 1, currY)||
			this.validMoveRook(currX, currY, currX - 1, currY)||
			this.validMoveRook(currX, currY, currX, currY + 1)||
			this.validMoveRook(currX, currY, currX, currY - 1)) {
			return true;
		}
		return false;
	}
	
	/**
	 * helper functions check movability of bishop or queen(diaganol)
	 * @param currPiece the input piece that need to be check if it can move
	 * @return true if the input can move otherwise false
	 */
	public boolean movableBishopOrQueen(Pieces currPiece) {
		int currX = currPiece.x;
		int currY = currPiece.y;
		if(this.validMoveBishop(currX, currY, currX + 1, currY + 1)||
			this.validMoveBishop(currX, currY, currX - 1, currY + 1)||
			this.validMoveBishop(currX, currY, currX + 1, currY - 1)||
			this.validMoveBishop(currX, currY, currX - 1, currY - 1)) {
			return true;
		}
//		if(this.validMoveBishop(currX, currY, currX - 1, currY + 1)) {
//			return true;
//		}
//		if(this.validMoveBishop(currX, currY, currX + 1, currY - 1)) {
//			return true;
//		}
//		if(this.validMoveBishop(currX, currY, currX - 1, currY - 1)) {
//			return true;
//		}
		return false;
	}

	/**
	 * helper functions check movability of knight
	 * @param currPiece the input piece that need to be check if it can move
	 * @return true if the input can move otherwise false
	 */
	public boolean movableKnight(Pieces currPiece) {
		int currX = currPiece.x;
		int currY = currPiece.y;
		if(this.validMoveKnight(currX, currY, currX + 1, currY + 2)||
			this.validMoveKnight(currX, currY, currX - 1, currY + 2)||
			this.validMoveKnight(currX, currY, currX + 1, currY - 2)||
			this.validMoveKnight(currX, currY, currX - 1, currY - 2)||
			this.validMoveKnight(currX, currY, currX + 2, currY + 1)||
			this.validMoveKnight(currX, currY, currX - 2, currY + 1)||
			this.validMoveKnight(currX, currY, currX + 2, currY - 1)||
			this.validMoveKnight(currX, currY, currX - 2, currY - 1)) {
			return true;
		}
		return false;
	}
	
	/**
	 * helper functions check movability of pawn
	 * @param currPiece the input piece that need to be check if it can move
	 * @return true if the input can move otherwise false
	 */
	public boolean movablePawn(Pieces currPiece) {
		int currX = currPiece.x;
		int currY = currPiece.y;
		if(currPiece.player == 1) {
			if(this.validMovePawn(currX, currY, currX + 1, currY + 1)||
				this.validMovePawn(currX, currY, currX, currY + 1)||
				this.validMovePawn(currX, currY, currX - 1, currY + 1)) {
				return true;
			}
		}
		else if(currPiece.player == 2){
			if(this.validMovePawn(currX, currY, currX + 1, currY - 1)||
				this.validMovePawn(currX, currY, currX, currY - 1)||
				this.validMovePawn(currX, currY, currX - 1, currY - 1)) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * stalemate main function:
	 * first check the king is not checked, if it is checked then it must be not stalement
	 * then check if the king could move without being checked if it can it is not stalement
	 * if the king cannot move then check if any one of the pieces can move
	 * @param player needed to be check if it is in stalemate condition
	 * @return true if the player is in stalemate condition otherwise false
	 */
	public boolean isStalemate(int player) {
		//if the king is checked it is not stalemate
		Pieces king = this.getKing(player);
		int oldKingX = king.x;
		int oldKingY = king.y;
		int numRookOrQueen = this.isCheckedByRookOrQueen(king);
		int numBishopOrQueen = this.isCheckedByBishopOrQueen(king);
		int numKnight = this.isCheckedByKnight(king);
		int numPawn = this.isCheckedByPawn(king);
		int numChecked = numRookOrQueen + numBishopOrQueen + numKnight + numPawn;
		if(numChecked > 0) return false;//if the king is checked then it cannot be stalemate
		//if the king can move and not be checked, it is not stalemate
		Pieces temp = this.getChessByPos(oldKingX - 1 , oldKingY - 1);
		if(this.moveChess(king.x, king.y, oldKingX - 1 , oldKingY - 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				board[oldKingX - 1][oldKingY - 1] = temp;
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
			board[oldKingX - 1][oldKingY - 1] = temp;
		}
		temp = this.getChessByPos(oldKingX + 1 , oldKingY + 1);
		if(this.moveChess(king.x, king.y, oldKingX + 1 , oldKingY + 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				board[oldKingX + 1][oldKingY + 1] = temp;
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
			board[oldKingX + 1][oldKingY + 1] = temp;
		}
		
		temp = this.getChessByPos( oldKingX - 1 , oldKingY + 1);
		if(this.moveChess(king.x, king.y, oldKingX - 1 , oldKingY + 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				board[oldKingX - 1][oldKingY + 1] = temp;
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
			board[oldKingX - 1][oldKingY + 1] = temp;
		}
		temp = this.getChessByPos(oldKingX + 1 , oldKingY - 1);
		if(this.moveChess(king.x, king.y, oldKingX + 1 , oldKingY - 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				board[oldKingX + 1][oldKingY - 1] = temp;
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
			board[oldKingX + 1][oldKingY - 1] = temp;
		}
		temp = this.getChessByPos(oldKingX , oldKingY - 1);
		if(this.moveChess(king.x, king.y, oldKingX , oldKingY - 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				board[oldKingX][oldKingY - 1] = temp;
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
			board[oldKingX][oldKingY - 1] = temp;
		}
		temp = this.getChessByPos(oldKingX , oldKingY + 1);
		if(this.moveChess(king.x, king.y, oldKingX , oldKingY + 1)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				board[oldKingX][oldKingY + 1] = temp;
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
			board[oldKingX][oldKingY + 1] = temp;
		}
		temp = this.getChessByPos(oldKingX - 1, oldKingY);
		if(this.moveChess(king.x, king.y, oldKingX - 1, oldKingY)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				board[oldKingX - 1][oldKingY] = temp;
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
			board[oldKingX - 1][oldKingY] = temp;
		}
		temp = this.getChessByPos(oldKingX - 1, oldKingY);
		if(this.moveChess(king.x, king.y, oldKingX + 1, oldKingY)) {
			if(!this.isChecked(king)) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				board[oldKingX + 1][oldKingY] = temp;
				return false;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
			board[oldKingX + 1][oldKingY] = temp;
		}
		//if the king cannot move check all other chesspieces
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Pieces currPiece = this.getChessByPos(i, j);
				if(currPiece == null) continue;
				if(currPiece.player == player) {
					if(currPiece.type == "Rook" || currPiece.type == "Queen") {
						if (this.movableRookOrQueen(currPiece))return false;
					}
					else if(currPiece.type == "Bishop" || currPiece.type == "Queen"){
						if(this.movableBishopOrQueen(currPiece))return false;
					}
					else if(currPiece.type == "Knight") {
						if(this.movableKnight(currPiece))return false;
					}
					else if (currPiece.type == "Pawn") {
						if(this.movablePawn(currPiece))return false;
					}
				}
			}
		}
		return true;
	}
	
	// game end when either checkmate or stalemate take place
	
}
