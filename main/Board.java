package main;

import java.util.ArrayList;
import java.util.Stack;
/**
 * 
 * @author kaichenle
 *
 */
public class Board {
	public Pieces[][] board = new Pieces[8][8];
	public boolean notGameEnd;
	public int winner;
	//turns = 1 player 1 turns
	//turns = 0 player 2 turns
	private boolean turns;
	public boolean getTurns () {
		return turns;
	}
	/**
	 * switch player turns
	 */
	public void switchTurns() {
		this.turns = !this.turns;
	}
	//stacks to track moving history
	private Stack<Integer> historyMoveX1;
	private Stack<Integer> historyMoveY1;
	private Stack<Integer> historyMoveX2;
	private Stack<Integer> historyMoveY2;
	/**
	 * check if it valid to undo
	 * @return true if it is valid otherwise false
	 */
	public boolean undoValid(){
		if(historyMoveX1.size()<2||historyMoveX2.size()<2){
			return false;
		}
		return true;
	}
	/**
	 * undo operation on the board
	 * users can undo when ever there are available undo step
	 * but undo would applies to both users
	 */
	public void undo () {
		if (!this.undoValid()){
			System.out.println ("Undo invalid");
			return;
		}
		int currX1 = historyMoveX1.pop();
		int currY1 = historyMoveY1.pop();
		int undoX1 = historyMoveX1.pop();
		int undoY1  = historyMoveY1.pop();
		int currX2 = historyMoveX2.pop();
		int currY2 = historyMoveY2.pop();
		int undoX2 = historyMoveX2.pop();
		int undoY2  = historyMoveY2.pop();


		if (board[currX1][currY1] instanceof Pawn) {
			if (undoY1 == 1) {
				((Pawn) board[currX1][currY1]).firstMove=true;
			}
		}
		if (board[currX2][currY2] instanceof Pawn) {
			if (undoY2 == 6) {
				((Pawn) board[currX2][currY2]).firstMove=true;
			}
		}
		board[undoX1][undoY1] = board[currX1][currY1];
		board[currX1][currY1]=null;
		board[undoX1][undoY1].x = undoX1;
		board[undoX1][undoY1].y = undoY1;

		board[undoX2][undoY2] = board[currX2][currY2];
		board[currX2][currY2]=null;
		board[undoX2][undoY2].x = undoX2;
		board[undoX2][undoY2].y = undoY2;
	}



	/*
	 * a constructor that will create a standard 8*8 chess board with chesses on it
	 */
	public Board() {
		historyMoveX1 = new Stack<Integer> ();
		historyMoveX2 = new Stack<Integer> ();
		historyMoveY1 = new Stack<Integer> ();
		historyMoveY2 = new Stack<Integer> ();
		winner= -1;
		turns = true;
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

//		historyMoveX1 = new Stack<Integer> ();
//		historyMoveX2 = new Stack<Integer> ();
//		historyMoveY1 = new Stack<Integer> ();
//		historyMoveY2 = new Stack<Integer> ();
//		winner= -1;
//		turns = true;
//		for (int i = 0; i < 8; i++) {
//			for(int j = 0; j < 8; j++) {
//				board[i][j]=null;
//			}
//		}
//		for (int i = 0; i < 8; i++) {
//			//board[i][1] = new Pawn(i, 1, 1);
//			//board[i][6] = new Pawn(i, 6, 2);
//		}
//		board[0][0] = new Rook(0, 0, 1);
//		board[7][0] = new Rook(7, 0, 1);
//		//board[0][7] = new Rook(0, 7, 2);
//		//board[7][7] = new Rook(7, 7, 2);
//
//		board[1][0] = new Knight(1, 0, 1);
//		board[6][0] = new Knight(6, 0, 1);
//		//board[1][7] = new Knight(1, 7, 2);
//		//board[6][7] = new Knight(6, 7, 2);
//
//		board[2][0] = new Bishop(2, 0, 1);
//		board[5][0] = new Bishop(5, 0,  1);
//		//board[2][7] = new Bishop(2, 7, 2);
//		//board[5][7] = new Bishop(5, 7, 2);
//
//		board[3][0] = new Queen(3, 0, 1);
//		//board[3][7] = new Queen(3, 7, 2);
//		board[4][0] = new King(4, 0, 1);
//		board[4][7] = new King(4, 7, 2);
	}

	/**
	 *  constructor for customize  board
	 * @param custom
	 */
	public Board(boolean custom) {
		if(custom == false) return;
		historyMoveX1 = new Stack<Integer> ();
		historyMoveX2 = new Stack<Integer> ();
		historyMoveY1 = new Stack<Integer> ();
		historyMoveY2 = new Stack<Integer> ();
		winner= -1;
		turns = true;
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
		board[1][3] = new Crab (1, 3 ,1);
		board[5][3] = new Ox (5, 3 ,1);
		board[6][4] = new Crab (6, 4,2);
		board[2][4] = new Ox(2, 4,2);

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
		if(this.getChessByPos(x, y).isValidMove(newX, newY) == false||this.getChessByPos(x,y).validMove(newX, newY,
				this)==false) {
			return false;
		}
		if(this.getChessByPos(x, y).type == "Pawn"){
			((Pawn)this.getChessByPos(x, y)).firstMove = false;
		}
		if(this.getChessByPos(x, y).player==1){
			Pieces start1 = board[x][y];
			Pieces end1 = board[newX][newY];
			historyMoveX1.push(x);
			historyMoveY1.push(y);
			historyMoveX1.push(newX);
			historyMoveY1.push(newY);

		}
		else{
			Pieces start2 = board[x][y];
			Pieces end2 = board[newX][newY];
			historyMoveX2.push(x);
			historyMoveY2.push(y);
			historyMoveX2.push(newX);
			historyMoveY2.push(newY);
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
	 * helper functions check if the chess is checked by Crab
	 * @param checkingPiece the piece be checked
	 * @return a arraylist with all checker
	 */
	public ArrayList<Pieces> isCheckedByCrab(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;
		ArrayList<Pieces> checkerList = new ArrayList<Pieces>();
		for(int i = x + 1; i < 8; i++) {
			if(this.getChessByPos(i, y)!=null) {
				if(this.getChessByPos(i, y).player == checkingPiece.player) {
					break;
				}
				else if(this.getChessByPos(i, y) instanceof Crab){
					checkerList.add(this.getChessByPos(i, y));
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
				else if(this.getChessByPos(i, y) instanceof Crab){
					checkerList.add(this.getChessByPos(i, y));
					break;
				}
				else {
					break;
				}
			}
		}
		return checkerList;
	}
	/**
	 * helper functions check if the chess is checked by Ox
	 * @param checkingPiece the piece be checked
	 * @return a arraylist with all checker
	 */
	public ArrayList<Pieces> isCheckedByOx(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;
		ArrayList<Pieces> checkerList = new ArrayList<Pieces>();
		for(int i = y + 1; i < 8; i++) {
			if(this.getChessByPos(x, i)!=null) {
				if(this.getChessByPos(x, i).player == checkingPiece.player) {
					break;
				}
				else if(this.getChessByPos(x, i) instanceof Ox){
					checkerList.add(this.getChessByPos(x, i));
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
				else if(this.getChessByPos(x, i) instanceof Ox){
					checkerList.add(this.getChessByPos(x, i));
					break;
				}
				else {
					break;
				}
			}
		}
		return checkerList;
	}


	/**
	 * helper functions check if the chess is checked by rook or queen(vertical and horizontal)
	 * @param checkingPiece the piece be checked
	 * @return a arraylist with all checker
	 */
	public ArrayList<Pieces> isCheckedByRookOrQueen(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;
		ArrayList<Pieces> checkerList = new ArrayList<Pieces>();
		for(int i = x + 1; i < 8; i++) {
			if(this.getChessByPos(i, y)!=null) {
				if(this.getChessByPos(i, y).player == checkingPiece.player) {
					break;
				}
				else if(this.getChessByPos(i, y) instanceof Queen || this.getChessByPos(i, y) instanceof Rook){
					checkerList.add(this.getChessByPos(i, y));
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
					checkerList.add(this.getChessByPos(i, y));
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
					checkerList.add(this.getChessByPos(x, i));
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
					checkerList.add(this.getChessByPos(x, i));
					break;
				}
				else {
					break;
				}
			}
		}
		return checkerList;
	}
	
	/**
	 * helper functions check if the chess is checked by bishop or queen(diaganol)
	 * @param checkingPiece the piece be checked
	 * @return a arraylist with all checker
	 */
	public ArrayList<Pieces> isCheckedByBishopOrQueen(Pieces checkingPiece) {
		ArrayList<Pieces> checkerList = new ArrayList<Pieces>();
		int x = checkingPiece.x;
		int y = checkingPiece.y;
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
				checkerList.add(this.getChessByPos(newX, newY));
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
				checkerList.add(this.getChessByPos(newX, newY));
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
				checkerList.add(this.getChessByPos(newX, newY));
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
				checkerList.add(this.getChessByPos(newX, newY));
				break;
			}
			else {
				break;
			}
		}
		return checkerList;
	}
	
	/**
	 * helper functions check if the chess is checked by knight
	 * @param checkingPiece the piece be checked
	 * @return a arraylist with all checker
	 */
	public ArrayList<Pieces> isCheckedByKnight(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;
		ArrayList<Pieces> checkerList = new ArrayList<Pieces>();
		if( x+2 >= 0 && x+2 < 8 && y+1 >=0 && y+1 < 8) {
			Pieces tempPiece = this.getChessByPos(x+2, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					checkerList.add(tempPiece);
				}
			}
		}
		if( x-2 >= 0 && x-2 < 8 && y+1 >=0 && y+1 < 8) {
			Pieces tempPiece = this.getChessByPos(x-2, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					checkerList.add(tempPiece);
				}
			}
		}
		if( x+2 >= 0 && x+2 < 8 && y-1 >=0 && y-1 < 8) {
			Pieces tempPiece = this.getChessByPos(x+2, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					checkerList.add(tempPiece);
				}
			}
		}
		if( x-2 >= 0 && x-2 < 8 && y-1 >=0 && y-1 < 8) {
			Pieces tempPiece = this.getChessByPos(x-2, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					checkerList.add(tempPiece);
				}
			}
		}
		if( x+1 >= 0 && x+1 < 8 && y+2 >=0 && y+2 < 8) {
			Pieces tempPiece = this.getChessByPos(x+1, y+2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					checkerList.add(tempPiece);
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y+2 >=0 && y+2 < 8) {
			Pieces tempPiece = this.getChessByPos(x-1, y+2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					checkerList.add(tempPiece);
				}
			}
		}
		if( x+1 >= 0 && x+1 < 8 && y-2 >=0 && y-2 < 8) {
			Pieces tempPiece = this.getChessByPos(x+1, y-2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					checkerList.add(tempPiece);
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y-2 >=0 && y-2 < 8) {
			Pieces tempPiece = this.getChessByPos(x-1, y-2);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Knight) {
					checkerList.add(tempPiece);
				}
			}
		}
		return checkerList;
	}	
	
	/**
	 * helper functions check if the chess is checked by Pawn
	 * @param checkingPiece the piece be checked
	 * @return a arraylist with all checker
	 */
	public ArrayList<Pieces> isCheckedByPawn(Pieces checkingPiece) {
		int x = checkingPiece.x;
		int y = checkingPiece.y;
		ArrayList<Pieces> checkerList = new ArrayList<Pieces>();
		int count = 0;
		if( x+1 >= 0 && x+1 < 8 && y+1 >=0 && y+1 < 8 && checkingPiece.player == 1) {
			Pieces tempPiece = this.getChessByPos(x+1, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					checkerList.add(tempPiece);
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y+1 >=0 && y+1 < 8 && checkingPiece.player == 1) {
			Pieces tempPiece = this.getChessByPos(x-1, y+1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					checkerList.add(tempPiece);
				}
			}
		}
		if( x+1 >= 0 && x+1 < 8 && y-1 >=0 && y-1 < 8 && checkingPiece.player == 2) {
			Pieces tempPiece = this.getChessByPos(x+1, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					checkerList.add(tempPiece);
				}
			}
		}
		if( x-1 >= 0 && x-1 < 8 && y-1 >=0 && y-1 < 8 && checkingPiece.player == 2) {
			Pieces tempPiece = this.getChessByPos(x-1, y-1);
			if(tempPiece!=null) {
				if(tempPiece.player !=checkingPiece.player && tempPiece instanceof Pawn) {
					checkerList.add(tempPiece);
				}
			}
		}
		return checkerList;
	}
	
	/**
	 * helper function see if the pieces is checked or not
	 * @param king the piece be checked usually the king
	 * @return number of checkers available
	 */
	public int isChecked(Pieces king) {
		if(king == null)return 0;
		int numRookOrQueen = this.isCheckedByRookOrQueen(king).size();
		int numBishopOrQueen = this.isCheckedByBishopOrQueen(king).size();

		int numKnight = this.isCheckedByKnight(king).size();
		int numPawn = this.isCheckedByPawn(king).size();
		int numCrab = this.isCheckedByCrab(king).size();
		int numOx = this.isCheckedByOx(king).size();
		return numRookOrQueen + numBishopOrQueen + numKnight + numPawn + numCrab + numOx;
	}

	/**
	 * check if move the king can make it not checked
	 * @param king the king need to be move
	 * @param newX the new X try to move the king to
	 * @param newY the new Y try to move the king to
	 * @return true if move the king can make the king not being checked false otherwise
	 */
	public boolean moveKingCanSolve(Pieces king, int newX, int newY){
		int oldKingX = king.x;
		int oldKingY = king.y;
		if(this.moveChess(king.x, king.y, newX , newY)) {
			if(this.isChecked(king) <=0 ) {
				this.moveChess(king.x, king.y, oldKingX , oldKingY);
				return true;
			}
			this.moveChess(king.x, king.y, oldKingX , oldKingY);
		}
		return false;
	}

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
		if(this.isChecked(king) <=0 ){
			return false;
		}
		//Check if move the king will solve the problem
		if(this.moveKingCanSolve(king, oldKingX - 1,oldKingY - 1)||
			this.moveKingCanSolve(king, oldKingX + 1,oldKingY + 1)||
			this.moveKingCanSolve(king, oldKingX - 1,oldKingY + 1)||
			this.moveKingCanSolve(king, oldKingX + 1,oldKingY - 1)||
			this.moveKingCanSolve(king, oldKingX ,oldKingY - 1)||
			this.moveKingCanSolve(king, oldKingX ,oldKingY + 1)||
			this.moveKingCanSolve(king, oldKingX - 1,oldKingY)||
			this.moveKingCanSolve(king, oldKingX + 1,oldKingY )){
			return false;
		}
		this.moveChess(king.x, king.y, oldKingX , oldKingY);
		//if king cannot be moved and more than checkers available then it must be checkMate
		if(this.isChecked(king) > 1) {
			if (player == 1 ){
				winner = 2;
			}
			else winner = 1;
			notGameEnd = false;
			return true;
		}//if king cannot be moved and only one checker available
		else {//we can try to either capture it or block it
			ArrayList<Pieces> theChecker = new ArrayList<Pieces>();
			theChecker.addAll(this.isCheckedByRookOrQueen(king));
			theChecker.addAll(this.isCheckedByBishopOrQueen(king));
			theChecker.addAll(this.isCheckedByKnight(king));
			theChecker.addAll(this.isCheckedByPawn(king));
			theChecker.addAll(this.isCheckedByCrab(king));
			theChecker.addAll(this.isCheckedByOx(king));
			Pieces checker = theChecker.get(0);
			if(this.isChecked(checker)>0) {
				return false;
			}
			if(checker.couldBeStopped(king.x, king.y, this)){
				return false;
			}
		}
		if (player == 1 ){
			winner = 2;
		}
		else winner = 1;

		return true;
	}
	
	//
	//stalemate
	//

	/**
	 * helper function for stalemate to see if the king can move around
	 * @param king the king need to be checked on
	 * @param newKingX if the king can move to newKingX
	 * @param newKingY if the king can move to newKingY
	 * @return true if the king can move in that direction otherwise false
	 */
	public boolean moveKingNotChecked(Pieces king, int newKingX, int newKingY){
		int oldKingX = king.x;
		int oldKingY = king.y;
		Pieces temp = this.getChessByPos(newKingX , newKingY);
		if(this.moveChess(oldKingX, oldKingY, newKingX , newKingY)) {
			if(this.isChecked(king)<=0) {
				this.moveChess(newKingX, newKingY, oldKingX , oldKingY);
				board[newKingX][newKingY] = temp;
				return true;
			}
			this.moveChess(newKingX, newKingY, oldKingX , oldKingY);
			board[newKingX][newKingY] = temp;
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
		int numChecked = this.isChecked(king);
		if(numChecked > 0) return false;//if the king is checked then it cannot be stalemate
		//if the king can move and not be checked, it is not stalemate
		if (this.moveKingNotChecked(king,oldKingX - 1, oldKingY - 1 )||
				this.moveKingNotChecked(king,oldKingX + 1, oldKingY + 1 )||
				this.moveKingNotChecked(king,oldKingX - 1, oldKingY + 1 )||
				this.moveKingNotChecked(king,oldKingX + 1, oldKingY - 1 )||
				this.moveKingNotChecked(king,oldKingX, oldKingY - 1 )||
				this.moveKingNotChecked(king,oldKingX, oldKingY + 1 )||
				this.moveKingNotChecked(king,oldKingX - 1, oldKingY)||
				this.moveKingNotChecked(king,oldKingX + 1, oldKingY)){
			return false;
		}
		//if the king cannot move check all other chesspieces
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Pieces currPiece = this.getChessByPos(i, j);
				if(currPiece == null) continue;
				if(currPiece.player == player) {
					if(currPiece instanceof King){
						continue;//skip the king because it is checked already and it need to be move without being chekced
					}
					if (currPiece.isMovable(this)){
						return false;
					}
				}
			}
		}
		this.winner = 0 ;
		return true;
	}
	
	// game end when either checkmate or stalemate take place
	
}
