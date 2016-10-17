package game;
import java.util.ArrayList;
import java.util.List;
import pieces.*;


public class Board {
	/*
	 * TODO:
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	/*
	 * BOARD INDEXES:
	 * 
	 * 8| 7| 6| 5| 4| 3| 2| 1|__ __ __ __ __ __ __ __ a b c d e f g h
	 */

	// KINGS
	King blackKing = new King(PieceColor.Black, new String[] { "d", "8" });
	King whiteKing = new King(PieceColor.White, new String[] { "d", "1" });
	// QUEENS
	Queen blackQueen = new Queen(PieceColor.Black, new String[] { "e", "8" });
	Queen whiteQueen = new Queen(PieceColor.White, new String[] { "e", "1" });
	// BISHOPS
	Bishop blackBishop1 = new Bishop(PieceColor.Black, new String[] { "c", "8" });
	Bishop blackBishop2 = new Bishop(PieceColor.Black, new String[] { "f", "8" });
	Bishop whiteBishop1 = new Bishop(PieceColor.White, new String[] { "c", "1" });
	Bishop whiteBishop2 = new Bishop(PieceColor.White, new String[] { "f", "1" });
	// KNIGHTS
	Knight blackKnight1 = new Knight(PieceColor.Black, new String[] { "b", "8" });
	Knight blackKnight2 = new Knight(PieceColor.Black, new String[] { "g", "8" });
	Knight whiteKnight1 = new Knight(PieceColor.White, new String[] { "b", "1" });
	Knight whiteKnight2 = new Knight(PieceColor.White, new String[] { "g", "1" });
	// ROOKS
	Rook blackRook1 = new Rook(PieceColor.Black, new String[] { "a", "8" });
	Rook blackRook2 = new Rook(PieceColor.Black, new String[] { "h", "8" });
	Rook whiteRook1 = new Rook(PieceColor.White, new String[] { "a", "1" });
	Rook whiteRook2 = new Rook(PieceColor.White, new String[] { "h", "1" });
	// PAWNS
	Pawn blackPawn1 = new Pawn(PieceColor.Black, new String[] { "a", "7" });
	Pawn blackPawn2 = new Pawn(PieceColor.Black, new String[] { "b", "7" });
	Pawn blackPawn3 = new Pawn(PieceColor.Black, new String[] { "c", "7" });
	Pawn blackPawn4 = new Pawn(PieceColor.Black, new String[] { "d", "7" });
	Pawn blackPawn5 = new Pawn(PieceColor.Black, new String[] { "e", "7" });
	Pawn blackPawn6 = new Pawn(PieceColor.Black, new String[] { "f", "7" });
	Pawn blackPawn7 = new Pawn(PieceColor.Black, new String[] { "g", "7" });
	Pawn blackPawn8 = new Pawn(PieceColor.Black, new String[] { "h", "7" });
	Pawn whitePawn1 = new Pawn(PieceColor.White, new String[] { "a", "2" });
	Pawn whitePawn2 = new Pawn(PieceColor.White, new String[] { "b", "2" });
	Pawn whitePawn3 = new Pawn(PieceColor.White, new String[] { "c", "2" });
	Pawn whitePawn4 = new Pawn(PieceColor.White, new String[] { "d", "2" });
	Pawn whitePawn5 = new Pawn(PieceColor.White, new String[] { "e", "2" });
	Pawn whitePawn6 = new Pawn(PieceColor.White, new String[] { "f", "2" });
	Pawn whitePawn7 = new Pawn(PieceColor.White, new String[] { "g", "2" });
	Pawn whitePawn8 = new Pawn(PieceColor.White, new String[] { "h", "2" });

	public List<Pieces> piecesList = new ArrayList<Pieces>();
	public String[][] board = new String[8][8];

	public Board(){
		addPieces();
		drawBoard();
	}
	public void changePosition(int x, int y, int new_x, int new_y) throws IllegalMoveException{
		
		if(board[y][x] == space){
			throw new IllegalMoveException();		}
		
		
		if(board[new_y][new_x] == space){
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
				board[y][x] = space;
			}
			else
			{throw new IllegalMoveException();}
			
		}
		
		reDrawBoard();		
	}
	
	private void addPieces() {
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

	public String space = " ___ ";
	public void drawBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = space;
			}
		}

		for (Pieces piece : piecesList) {
			String[] position = piece.getPosition();
			int x = positionLettertoInt(position[0]);
			int y = Integer.parseInt(position[1]) - 1;
			board[y][x] = piece.getCharRepresentation();
		}

		for (int i = 0; i < 8; i++) {
			System.out.println("");
			for (int j = 0; j < 8; j++) {
				System.out.print(board[i][j]);
			}
		}

		System.out.println("");
		System.out.println("");
		
		System.out.println("Enter your move: {letter-from number-from letter-to letter-to}");

	}
	
	public void reDrawBoard(){
		for (int i = 0; i < 8; i++) {
			System.out.println("");
			for (int j = 0; j < 8; j++) {
				System.out.print(board[i][j]);
			}
		}
		
		System.out.println("");
		System.out.println("");
		
		System.out.println("Enter your move: {letter-from number-from letter-to letter-to}");

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

}