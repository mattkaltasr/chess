package game;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import gamePieces.*;


/**
 * @author matt kalita and Yigit Gungor
 *
 */
public class Board {
	
	
	
	
	private int[][] private_white;
	private int[][] private_black;
	
	
	
	/*
	 * TODO:
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private Square[][] gameBoard;
	private HashMap<PieceType, Integer> whitePlayersPieces;
	private HashMap<PieceType, Integer> blackPlayersPieces;
	public static final char[] rowschar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
	/*
	 * BOARD INDEXES:
	 * 
	 * 8| 7| 6| 5| 4| 3| 2| 1|__ __ __ __ __ __ __ __ a b c d e f g h
	 */
	
	
	//constructor of board 
	public Board() {
		
		private_white = new int[8][8];
		
		gameBoard = new Square[8][8];
		whitePlayersPieces = new HashMap<PieceType, Integer>();
		
		private_black = new int[8][8];
		
		blackPlayersPieces = new HashMap<PieceType, Integer>();
		//initializes new board sets color integer for making game board 
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				int color;
				if (row % 2 == 0) {
					if (col % 2 == 0) {
						color = 1;
					}
					else {
						color = 0;
					}
				}
				else {
					if (col % 2 == 0) {
						color = 0;
					}
					else {
						color = 1;
					}
				}
				gameBoard[row][col] = new Square(rowschar[col], row, col, color);
			}
		}
	}
	
	
	//initializing pieces
	
	public void initialize() {
		for (int column = 0; column < 8; column++) {
			gameBoard[1][column].occupy(new Pawn(1, column, PieceColor.Black));
			gameBoard[6][column].occupy(new Pawn(6, column, PieceColor.White));
			if (column == 0 || column == 7) {
				gameBoard[0][column].occupy(new Rook(0, column, PieceColor.Black));
				gameBoard[7][column].occupy(new Rook(7, column, PieceColor.White));
			}
			else if (column == 1 || column == 6) {
				gameBoard[0][column].occupy(new Knight(0, column, PieceColor.Black));
				gameBoard[7][column].occupy(new Knight(7, column, PieceColor.White));
			}
			else if (column == 2 || column == 5) {
				gameBoard[0][column].occupy(new Bishop(0, column, PieceColor.Black));
				gameBoard[7][column].occupy(new Bishop(7, column, PieceColor.White));
			}
			else if (column == 3) {
				gameBoard[0][column].occupy(new Queen(0, column, PieceColor.Black));
				gameBoard[7][column].occupy(new Queen(7, column, PieceColor.White));
			}
			else {
				gameBoard[0][column].occupy(new King(0, column, PieceColor.Black));
				gameBoard[7][column].occupy(new King(7, column, PieceColor.White));
			}
		}
	}
	
	public List<Pieces> piecesList = new ArrayList<Pieces>();
	public String[][] board = new String[8][8];

	

	

	public Square getSquare(int row, int column) {
		return gameBoard[row][column];
	}
	
	public HashMap<PieceType, Integer> getBlackCounts() {
		return blackPlayersPieces;
	}
	
	public HashMap<PieceType, Integer> getWhiteCounts() {
		return whitePlayersPieces;
	}
	public int[][] getCoveredWhite() {
		return private_white;
	}

	//Array takes [ROW][COL] but chess positions are [COL][ROW]
	//So board[y][x] y=a x=2 is actually A2 on the board
	public void changePosition(int x, int y, int new_x, int new_y, boolean white) throws IllegalMoveException{
		
		Pieces piece = null;
		for(Pieces eachpiece: piecesList){
			int piece_x = positionLettertoInt(eachpiece.getPosition_x());
			int piece_y = (Integer.parseInt(eachpiece.getPosition_y()) - 1);
			if(piece_x == x && piece_y == y){
				piece = eachpiece;
				break;				
				}
		}
		
		if(board[y][x] == space){
			throw new IllegalMoveException();		}
		
		if(white && board[y][x].contains("b")){
			throw new IllegalMoveException();		}

		if(!white && board[y][x].contains("w")){
			throw new IllegalMoveException();		}
		

		
		
		if(board[new_y][new_x] == space){
			piece.setPosition(new String[] {positionInttoLetter(new_x),String.valueOf(new_y)});
			board[new_y][new_x] = board[y][x];
			board[y][x] = space;
		}else{

			if((board[y][x]).contains("BK") && (board[new_y][new_x]).contains("WT"))
			{
				board[new_y][new_x] = board[y][x];
				board[y][x] = space;
			}
			else if((board[y][x]).contains("WT") && (board[new_y][new_x]).contains("BK"))
			{
				board[new_y][new_x] = board[y][x];

			if((board[y][x]).contains("b") && (board[new_y][new_x]).contains("w"))
			{
				board[new_y][new_x] = board[y][x];
				piece.setPosition(new String[] {positionInttoLetter(new_x),String.valueOf(new_y)});
				board[y][x] = space;
			}
			else if((board[y][x]).contains("w") && (board[new_y][new_x]).contains("b"))
			{
				board[new_y][new_x] = board[y][x];
				piece.setPosition(new String[] {positionInttoLetter(new_x),String.valueOf(new_y)});

				board[y][x] = space;
			}
			else
			{throw new IllegalMoveException();}
			
		}
		
		reDrawBoard();}		
	}
	
	private void initializePieceCount	() {
		piecesList.add(blackKing);
		piecesList.add(whiteKing);

		piecesList.add(blackQueen);
		piecesList.add(whiteQueen);

		piecesList.add(blackBishop1);
		piecesList.add(blackBishop2);
		piecesList.add(whiteBishop1);
		piecesList.add(whiteBishop2);

		piecesList.add(blackKnight1);
		piecesList.add(blackKnight2);
		piecesList.add(whiteKnight1);
		piecesList.add(whiteKnight2);

		piecesList.add(blackRook1);
		piecesList.add(blackRook2);
		piecesList.add(whiteRook1);
		piecesList.add(whiteRook2);

		piecesList.add(blackPawn1);
		piecesList.add(blackPawn2);
		piecesList.add(blackPawn3);
		piecesList.add(blackPawn4);
		piecesList.add(blackPawn5);
		piecesList.add(blackPawn6);
		piecesList.add(blackPawn7);
		piecesList.add(blackPawn8);
		piecesList.add(whitePawn1);
		piecesList.add(whitePawn2);
		piecesList.add(whitePawn3);
		piecesList.add(whitePawn4);
		piecesList.add(whitePawn5);
		piecesList.add(whitePawn6);
		piecesList.add(whitePawn7);
		piecesList.add(whitePawn8);
	}

	public String space = "";
	public String bspace = " ## ";
	public String wspace = "    ";


	
	

	//prints out the virtual type board that is represented in this game 
		public void printBoard() {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					System.out.print(gameBoard[row][col].display() + " ");
				}
				System.out.println(8 - row);
			}
			for (int print = 0; print < 8; print++) {
				System.out.print(" " + rowschar[print] + " ");
			}
			System.out.println();
		}
		
		// prints out the text version of board
		public void printNotation() {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					System.out.print(gameBoard[row][col].getNotation() + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	public void reDrawBoard(){
		for (int i = 0; i < 8; i++) {
			System.out.println("");
			for (int j = 0; j < 8; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println(" " + (i+1));
		}
		
		System.out.println("");

		System.out.println("");
		
		System.out.println("Enter your move: {letter-from number-from letter-to letter-to}");

		System.out.println("  a   b   c   d   e   f   g   h");



	}
		
	public int positionLettertoInt(String letter) {
		switch (letter) {
		case "a":
			return 0;
		case "b":
			return 1;
		case "c":
			return 2;
		case "d":
			return 3;
		case "e":
			return 4;
		case "f":
			return 5;
		case "g":
			return 6;
		case "h":
			return 7;

		}
		return -1;

	}

	public Boolean pathNotBlocked(int oldx ,int oldy,int destx,int desty){
		//if(Math.abs((oldx-destx)==1&&Math.abs(oldy-desty)==0)||(oldx-destx)==0&&Math.abs(oldy-desty)==1))
		return false;
		
	}

	public String positionInttoLetter(int number) {
		switch (number) {
		case 0:
			return "a";
		case 1:
			return "b";
		case 2:
			return "c";
		case 3:
			return "d";
		case 4:
			return "e";
		case 5:
			return "f";
		case 6:
			return "g";
		case 7:
			return "h";


		}
		return "x";

	}
	//prints out the protected squares when called 
	
		public void printProtectedSquares() {
			System.out.println("Black Squares:");
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					System.out.print(private_black[row][col] + " ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("White squares :");
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					System.out.print(private_white[row][col] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		//prints out sybols square to string method called 
		public void printCoordinates() {
			for (int c = 0; c < 8; c++) {
				for (int r = 0; r < 8; r++) {
					System.out.print(gameBoard[c][r].toString() + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		public int[][] getCoveredBlack() {
			return private_black;
		}
		
	
}
