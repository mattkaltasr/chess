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
	
	// added 
	
	
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
	//cut code down by adding a loop to do task 
	
	
	/**
	 * used a loop to initialize pieces for game easier than loading individually 
	 */
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

	

	
/**
 * Getter method to return square 
 * @param row
 * @param column
 * @return
 */
	public Square getSquare(int row, int column) {
		return gameBoard[row][column];
	}
	/**
	 * getter method to return hash map of black pieces 
	 * @return
	 */
	public HashMap<PieceType, Integer> getBlackCounts() {
		return blackPlayersPieces;
	}
	/**
	 * getter to return hashmap of white players pieces 
	 * @return
	 */
	public HashMap<PieceType, Integer> getWhiteCounts() {
		return whitePlayersPieces;
	}
	
	/**
	 * gets the protected squares of white 
	 * @return
	 */
	public int[][] getCoveredWhite() {
		return private_white;
	}

	//Array takes [ROW][COL] but chess positions are [COL][ROW]
	//So board[y][x] y=a x=2 is actually A2 on the board
	
	
	// initializes game pieces 
		public void initializePieceCounts() {
			whitePlayersPieces.put(PieceType.PAWN, 8);
			whitePlayersPieces.put(PieceType.QUEEN, 1);
			whitePlayersPieces.put(PieceType.ROOK, 2);
			whitePlayersPieces.put(PieceType.KNIGHT, 2);
			whitePlayersPieces.put(PieceType.BISHOP, 2);
			blackPlayersPieces.put(PieceType.PAWN, 8);
			blackPlayersPieces.put(PieceType.QUEEN, 1);
			blackPlayersPieces.put(PieceType.ROOK, 2);
			blackPlayersPieces.put(PieceType.KNIGHT, 2);
			blackPlayersPieces.put(PieceType.BISHOP, 2);
		}
		
	public String space = "";
	public String bspace = " ## ";
	public String wspace = "    ";

	// resets the array thats protected 
	
	/**
	 * reset the 2 d array 
	 */
		public void resetCoveredSquares() {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					private_white[i][j] = 0;
					private_black[i][j] = 0;
				}
			}
		}
	
	

	//prints out the virtual type board that is represented in this game 
		/**
		 * prints out the board in text version not gui 
		 */
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
		/**
		 * prints out all the squares associated on the board and their respective toString method 
		 */
		public void printNotation() {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					System.out.print(gameBoard[row][col].getNotation() + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		
		
		/**
		 * void method to print out the  board 
		 */
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
	/**
	 * returns a boolean if cant move 
	 * @return
	 */
	public boolean draw() {
		//check-mate is possible if there are queens and/or rooks and/or pawns on the board
		if (whitePlayersPieces.get(PieceType.QUEEN) > 0 || blackPlayersPieces.get(PieceType.QUEEN) > 0 || 
				whitePlayersPieces.get(PieceType.ROOK) > 0 || blackPlayersPieces.get(PieceType.ROOK) > 0 ||
				whitePlayersPieces.get(PieceType.PAWN) > 0 || blackPlayersPieces.get(PieceType.PAWN) > 0) {
			return false;
		}
		else {
			//can check-mate with 2+ bishops
			if (whitePlayersPieces.get(PieceType.BISHOP) >= 2 || blackPlayersPieces.get(PieceType.BISHOP) >= 2) {
				return false;
			}
			return true;
		}
	}
	//prints out the protected squares when called 
	/**
	 * print method to print out the squares 
	 * not called 
	 */
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
		/**
		 * prints out the coordinates of the board 
		 * revision 
		 */
		public void printCoordinates() {
			for (int c = 0; c < 8; c++) {
				for (int r = 0; r < 8; r++) {
					System.out.print(gameBoard[c][r].toString() + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		/**
		 * getter method to return black squares 
		 * @return
		 */
		public int[][] getCoveredBlack() {
			return private_black;
		}
		
	
}
