package game;
import java.util.ArrayList;
import java.util.List;

import game.Location.*;
import pieces.*;


public class TestBoard {
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
	
	// Locations
	Location a1 = new Location(locationName.a1);
	Location a2 = new Location(locationName.a2);
	Location a3 = new Location(locationName.a3);
	Location a4 = new Location(locationName.a4);
	Location a5 = new Location(locationName.a5);
	Location a6 = new Location(locationName.a6);
	Location a7 = new Location(locationName.a7);
	Location a8 = new Location(locationName.a8);	
	Location b1 = new Location(locationName.b1);
	Location b2 = new Location(locationName.b2);
	Location b3 = new Location(locationName.b3);
	Location b4 = new Location(locationName.b4);
	Location b5 = new Location(locationName.b5);
	Location b6 = new Location(locationName.b6);
	Location b7 = new Location(locationName.b7);
	Location b8 = new Location(locationName.b8);	
	Location c1 = new Location(locationName.c1);
	Location c2 = new Location(locationName.c2);
	Location c3 = new Location(locationName.c3);
	Location c4 = new Location(locationName.c4);
	Location c5 = new Location(locationName.c5);
	Location c6 = new Location(locationName.c6);
	Location c7 = new Location(locationName.c7);
	Location c8 = new Location(locationName.c8);
	Location d1 = new Location(locationName.d1);
	Location d2 = new Location(locationName.d2);
	Location d3 = new Location(locationName.d3);
	Location d4 = new Location(locationName.d4);
	Location d5 = new Location(locationName.d5);
	Location d6 = new Location(locationName.d6);
	Location d7 = new Location(locationName.d7);
	Location d8 = new Location(locationName.d8);
	Location e1 = new Location(locationName.e1);
	Location e2 = new Location(locationName.e2);
	Location e3 = new Location(locationName.e3);
	Location e4 = new Location(locationName.e4);
	Location e5 = new Location(locationName.e5);
	Location e6 = new Location(locationName.e6);
	Location e7 = new Location(locationName.e7);
	Location e8 = new Location(locationName.e8);	
	Location f1 = new Location(locationName.f1);
	Location f2 = new Location(locationName.f2);
	Location f3 = new Location(locationName.f3);
	Location f4 = new Location(locationName.f4);
	Location f5 = new Location(locationName.f5);
	Location f6 = new Location(locationName.f6);
	Location f7 = new Location(locationName.f7);
	Location f8 = new Location(locationName.f8);	
	Location g1 = new Location(locationName.f1);
	Location g2 = new Location(locationName.f2);
	Location g3 = new Location(locationName.f3);
	Location g4 = new Location(locationName.f4);
	Location g5 = new Location(locationName.f5);
	Location g6 = new Location(locationName.f6);
	Location g7 = new Location(locationName.f7);
	Location g8 = new Location(locationName.f8);	
	Location h1 = new Location(locationName.h1);
	Location h2 = new Location(locationName.h2);
	Location h3 = new Location(locationName.h3);
	Location h4 = new Location(locationName.h4);
	Location h5 = new Location(locationName.h5);
	Location h6 = new Location(locationName.h6);
	Location h7 = new Location(locationName.h7);
	Location h8 = new Location(locationName.h8);
	
	

	// KINGS
	King blackKing = new King(PieceColor.Black, e1);
	King whiteKing = new King(PieceColor.White, e8);
	// QUEENS
	Queen blackQueen = new Queen(PieceColor.Black, d1);
	Queen whiteQueen = new Queen(PieceColor.White, d8);
	// BISHOPS
	Bishop blackBishop1 = new Bishop(PieceColor.Black, c1);
	Bishop blackBishop2 = new Bishop(PieceColor.Black, f1);
	Bishop whiteBishop1 = new Bishop(PieceColor.White, c8);
	Bishop whiteBishop2 = new Bishop(PieceColor.White, f8);
	// KNIGHTS
	Knight blackKnight1 = new Knight(PieceColor.Black, b1);
	Knight blackKnight2 = new Knight(PieceColor.Black, g1);
	Knight whiteKnight1 = new Knight(PieceColor.White, b8);
	Knight whiteKnight2 = new Knight(PieceColor.White, g8);
	// ROOKS
	Rook blackRook1 = new Rook(PieceColor.Black, a1);
	Rook blackRook2 = new Rook(PieceColor.Black, h1);
	Rook whiteRook1 = new Rook(PieceColor.White, a8);
	Rook whiteRook2 = new Rook(PieceColor.White, h8);
	// PAWNS
	Pawn blackPawn1 = new Pawn(PieceColor.Black, a2);
	Pawn blackPawn2 = new Pawn(PieceColor.Black, b2);
	Pawn blackPawn3 = new Pawn(PieceColor.Black, c2);
	Pawn blackPawn4 = new Pawn(PieceColor.Black, d2);
	Pawn blackPawn5 = new Pawn(PieceColor.Black, e2);
	Pawn blackPawn6 = new Pawn(PieceColor.Black, f2);
	Pawn blackPawn7 = new Pawn(PieceColor.Black, g2);
	Pawn blackPawn8 = new Pawn(PieceColor.Black, h2);
	Pawn whitePawn1 = new Pawn(PieceColor.White, a7);
	Pawn whitePawn2 = new Pawn(PieceColor.White, b7);
	Pawn whitePawn3 = new Pawn(PieceColor.White, c7);
	Pawn whitePawn4 = new Pawn(PieceColor.White, d7);
	Pawn whitePawn5 = new Pawn(PieceColor.White, e7);
	Pawn whitePawn6 = new Pawn(PieceColor.White, f7);
	Pawn whitePawn7 = new Pawn(PieceColor.White, g7);
	Pawn whitePawn8 = new Pawn(PieceColor.White, h7);

	public List<Pieces> piecesList = new ArrayList<Pieces>();
	public List<Location> board = new ArrayList<Location>();

	public TestBoard(){
		addPieces();
		addLocations();
		drawBoard();
	}
	

	//Array takes [ROW][COL] but chess positions are [COL][ROW]
	//So board[y][x] y=a x=2 is actually A2 on the board
	
	public void changePosition(String fromLocation, String toLocation,boolean white) throws IllegalMoveException{
		

		Location from = null;
		Location to = null;
		
		for(Location l : board)
		{ 
			if(l.name.name().toString().trim().equals(fromLocation)){
				from = l;
			}
			if(l.name.name().toString().trim().equals(toLocation)){
				to = l;
			}
		}
		if(from == null || to == null){
			throw new IllegalMoveException();
		}
		
		Pieces piece = from.piece;
		
		if(white){
			if(piece.getColor() != PieceColor.White)
				throw new IllegalMoveException();
		}
		else{
			if(piece.getColor() != PieceColor.Black)
				throw new IllegalMoveException();
		}
		
		if(piece == null){
			throw new IllegalMoveException();
		}
		if(to.piece != null){
			
				if(piece.getCharRepresentation().contains("w") && to.piece.getCharRepresentation().contains("w")){
						throw new IllegalMoveException();		}
				
				if(piece.getCharRepresentation().contains("b") && to.piece.getCharRepresentation().contains("b")){
						throw new IllegalMoveException();		}
		}
		//Check if move is legal
			
			piece.location = to;
			from.piece = null;
			to.piece = piece;
		
		drawBoard();		
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
	private void addLocations(){
		board.add(a1);
		board.add(a2);
		board.add(a3);
		board.add(a4);
		board.add(a5);
		board.add(a6);
		board.add(a7);
		board.add(a8);		
		board.add(b1);
		board.add(b2);
		board.add(b3);
		board.add(b4);
		board.add(b5);
		board.add(b6);
		board.add(b7);
		board.add(b8);		
		board.add(c1);
		board.add(c2);
		board.add(c3);
		board.add(c4);
		board.add(c5);
		board.add(c6);
		board.add(c7);
		board.add(c8);		
		board.add(d1);
		board.add(d2);
		board.add(d3);
		board.add(d4);
		board.add(d5);
		board.add(d6);
		board.add(d7);
		board.add(d8);		
		board.add(e1);
		board.add(e2);
		board.add(e3);
		board.add(e4);
		board.add(e5);
		board.add(e6);
		board.add(e7);
		board.add(e8);		
		board.add(f1);
		board.add(f2);
		board.add(f3);
		board.add(f4);
		board.add(f5);
		board.add(f6);
		board.add(f7);
		board.add(f8);		
		board.add(g1);
		board.add(g2);
		board.add(g3);
		board.add(g4);
		board.add(g5);
		board.add(g6);
		board.add(g7);
		board.add(g8);		
		board.add(h1);
		board.add(h2);
		board.add(h3);
		board.add(h4);
		board.add(h5);
		board.add(h6);
		board.add(h7);
		board.add(h8);
		
	}
	
	
	public void drawBoard() {
		System.out.println(a1.toString() + b1.toString() + c1.toString() + d1.toString() + e1.toString() + f1.toString() + g1.toString() + h1.toString() + " | 1");
		System.out.println(a2.toString() + b2.toString() + c2.toString() + d2.toString() + e2.toString() + f2.toString() + g2.toString() + h2.toString() + " | 2");
		System.out.println(a3.toString() + b3.toString() + c3.toString() + d3.toString() + e3.toString() + f3.toString() + g3.toString() + h3.toString() + " | 3");
		System.out.println(a4.toString() + b4.toString() + c4.toString() + d4.toString() + e4.toString() + f4.toString() + g4.toString() + h4.toString() + " | 4");
		System.out.println(a5.toString() + b5.toString() + c5.toString() + d5.toString() + e5.toString() + f5.toString() + g5.toString() + h5.toString() + " | 5");
		System.out.println(a6.toString() + b6.toString() + c6.toString() + d6.toString() + e6.toString() + f6.toString() + g6.toString() + h6.toString() + " | 6");
		System.out.println(a7.toString() + b7.toString() + c7.toString() + d7.toString() + e7.toString() + f7.toString() + g7.toString() + h7.toString() + " | 7");
		System.out.println(a8.toString() + b8.toString() + c8.toString() + d8.toString() + e8.toString() + f8.toString() + g8.toString() + h8.toString() + " | 8");		
		System.out.println("_______________________________");
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

	
}
