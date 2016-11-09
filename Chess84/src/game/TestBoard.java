package game;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ChessGame.BlockedMoveException;
import GameBoards.Board;
import GameBoards.Square;
import gamePieces.*;


public class TestBoard {
	
	static int lastPawnMoveOrCapture = 0;
	static ArrayList<String> white_moves = new ArrayList<String>();// keep track of players piece
	static ArrayList<String> black_moves = new ArrayList<String>();// keep track of players pieces 
	
	//player list to keep track of current pieces player has left and for stale-mate,check 
	public List<Pieces>  blackPlayer=new ArrayList<Pieces>();
	public List<Pieces>  whitePlayer=new ArrayList<Pieces>();
	static boolean check = false; //if check it will be tru 
	static boolean end = false; // if end of game it will be true 
	
	public TestBoard(){
		
	}
	

	//Array takes [ROW][COL] but chess positions are [COL][ROW]
	//So board[y][x] y=a x=2 is actually A2 on the board
	
	public static void movePiece(String fromLocation, String toLocation,boolean white) throws IllegalMoveException{
		

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
			throw new IllegalMoveException("no pieces to move from there ,Try again");
		}
		
		Pieces piece = from.piece;
		
		if(white){
			if(piece.getColor() != PieceColor.White)
				throw new IllegalMoveException(" Cant Move ,try again ");
		}
		else{
			if(piece.getColor() != PieceColor.Black)
				throw new IllegalMoveException(" Cant Move ,try again ");
		}
		
		if(piece == null){
			throw new IllegalMoveException("no Game peice to move");
		}
		if(to.piece != null){
			
				if(piece.getCharRepresentation().contains("w") && to.piece.getCharRepresentation().contains("w")){
						throw new IllegalMoveException();		}
				
				if(piece.getCharRepresentation().contains("b") && to.piece.getCharRepresentation().contains("b")){
						throw new IllegalMoveException();		}
		}
		/*Check if move is legal
			if(white){
				if(notInCheck((int)whiteKingplace.getX(),(int)whiteKingplace.getY()))
					throw new IllegalMoveException("Can't move into check");
				else 
					notInCheck((int)blackKingPlace.getX(),(int)blackKingPlace.getY());
				throw new IllegalMoveException("Can't move into check");
//			}*/
			piece.location = to;
			//removal of piece from players array 
			if(from.piece!=null){
				if(from.piece.getColor()==PieceColor.White)
					whitePlayer.remove(from.piece)	;
				else
					blackPlayer.remove(from.piece);
					
			}
			from.piece = null;
			to.piece = piece;
		
		drawBoard();		
	}
	/**
	 * getter method to return last pawn moved or captured 
	 * @return
	 */
	public static int getLastPawnMoveOrCapture() {
		return lastPawnMoveOrCapture;
	}
	
	/**
	 * Mutator void method that sets the boolean to true if ended 
	 */
	public static void setEnd() {
		end = true;
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

	
	/**
	 * Switch that checks the path of each type of piece to see if it is blocked or not 
	 * a separate case for each piece along with checking the path for the type of moves that piece does 
	 * 
	 * @param board receives current board in play to check path 
	 * @param fromRow initial row in rep 
	 * @param fromcolumn initial row int rep 
	 * @param toRow destination row int  rep 
	 * @param toColumn destination column int rep 
	 * @throws BlockedMoveException throws this if can't move due to blocked path ie piece of your own or other players in way 
	 */
	private static void isPathBlocked(Board board, int fromRow, int fromcolumn, int toRow, int toColumn) throws BlockedMoveException {
		
		Square start = board.getSquare(fromRow, fromcolumn);
		Square dest = board.getSquare(toRow, toColumn);
		Pieces player = start.getPiece();
		//check path
		PieceColor current = board.getSquare(fromRow, fromcolumn).getPiece().getColor();
		Square pathSquare;
		switch(player.getPieceType()) {
			
		case QUEEN:// need to check all moves of queen in all direction 
			int queen;
			int row, column;
			//if columns  are the same just same as rook case above 
			if (fromcolumn == toColumn) {
				if (fromRow < toRow) {
					for (queen = fromRow + 1; queen < toRow; queen++) {
						pathSquare = board.getSquare(queen, fromcolumn);
						if (!pathSquare.isEmpty()) {
							throw new BlockedMoveException();
						}
					}
				}
				else {
					for (queen = fromRow - 1; queen > toRow; queen--) {
						pathSquare = board.getSquare(queen, fromcolumn);
						if (!pathSquare.isEmpty()) {
							throw new BlockedMoveException();	}	}
				}
			}
			//otherwise rows are the same used similar code to rooks move above 
			else if (fromRow == toRow){
				if (fromcolumn < toColumn) {
					for (queen = fromcolumn + 1; queen < toColumn; queen++) {
						pathSquare = board.getSquare(fromRow, queen);
						if (!pathSquare.isEmpty()) {
							throw new BlockedMoveException();
						}
					}
				}
				else {
					for (queen = fromcolumn - 1; queen > toColumn; queen--) {
						pathSquare = board.getSquare(fromRow, queen);
						if (!pathSquare.isEmpty()) {
							throw new BlockedMoveException();	}		}
				}
			}
			//going up the board 
			if (fromRow > toRow) {
				//going left
				row = fromRow - 1;
								if (fromcolumn > toColumn) {
					column = fromcolumn - 1;
					while (row > toRow && column > toColumn) {
						pathSquare = board.getSquare(row, column);
						if (!pathSquare.isEmpty()) {
							throw new BlockedMoveException();
						}
						row--;
						column--;
					}
				}
				//going right on the board 
				else {
					column = fromcolumn + 1;
					while (row > toRow && column < toColumn) {
						pathSquare = board.getSquare(row, column);
						if (!pathSquare.isEmpty()) {
							throw new BlockedMoveException();
						}
						row--;
						column++;
					}
				}
			}
			//going down the board 
			else {
				row = fromRow + 1;
				//going left from that point 
				if (fromcolumn > toColumn) {
					column = fromcolumn - 1;
					while (row < toRow && column > toColumn) {
						pathSquare = board.getSquare(row, column);
						if (!pathSquare.isEmpty()) {
							throw new BlockedMoveException();
						}
						row++;
						column--;
					}
				}
				//going right from that point 
				else {
					column = fromcolumn + 1;
					while (row < toRow && column < toColumn) {
						pathSquare = board.getSquare(row, column);
						if (!pathSquare.isEmpty()) {
							throw new BlockedMoveException();
						}
						row++;
						column++;
					}
				}
			}
			break;
			case BISHOP:
				//going in the upper direction of the board
				
				if (fromRow > toRow) {
					//going leftward on board 
					row = fromRow - 1;
					if (fromcolumn > toColumn) {
						column = fromcolumn - 1;
						while (row > toRow && column > toColumn) {
							pathSquare = board.getSquare(row, column);
							if (!pathSquare.isEmpty()) {
								throw new BlockedMoveException();
							}
							row--;
							column--;
						}
					}
					//going rightward on board 
					else {
						column = fromcolumn + 1;
						while (row > toRow && column < toColumn) {
							pathSquare = board.getSquare(row, column);
							if (!pathSquare.isEmpty()) {
								throw new BlockedMoveException();
							}
							row--;
							column++;
						}
					}
				}
				//going down if needed on board 
				else {
					row = fromRow + 1;
					//going left
					if (fromcolumn > toColumn) {
						column = fromcolumn - 1;
						while (row < toRow && column > toColumn) {
							pathSquare = board.getSquare(row, column);
							if (!pathSquare.isEmpty()) {
								throw new BlockedMoveException();
							}
							row++;
							column--;
						}
					}
					//going right on board 
					else {
						column = fromcolumn + 1;
						while (row < toRow && column < toColumn) {
							pathSquare = board.getSquare(row, column);
							if (!pathSquare.isEmpty()) {
								throw new BlockedMoveException();
							}
							row++;
							column++;					}
					}
				}
				break;
				
			case ROOK:
				int a;
				//if columns are the same it checks 
				if (fromcolumn == toColumn) {
					if (fromRow < toRow) {
						for (a = fromRow + 1; a < toRow; a++) {
							pathSquare = board.getSquare(a, fromcolumn);
							if (!pathSquare.isEmpty()) {
								throw new BlockedMoveException();	}
						}
					}
					else {
						for (a = fromRow - 1; a > toRow; a--) {
							pathSquare = board.getSquare(a, fromcolumn);
							if (!pathSquare.isEmpty()) {
								throw new BlockedMoveException();	}			}
					}
				}
				//otherwise rows are the same and it moves on rows 
				else if (fromRow == toRow){
					if (fromcolumn < toColumn) {
						for (a = fromcolumn + 1; a < toColumn; a++) {
							pathSquare = board.getSquare(fromRow, a);
							if (!pathSquare.isEmpty()) {
								throw new BlockedMoveException();
							}
						}
					}
					else {
						for (a = fromcolumn - 1; a > toColumn; a--) {
							pathSquare = board.getSquare(fromRow, a);
							if (!pathSquare.isEmpty()) {
								throw new BlockedMoveException();	}		}
					}
				}
				break;
			
				
			case PAWN: 
				if (!player.hasMoved()) {
					if (player.getColor() == PieceColor.Black) {
						pathSquare = board.getSquare(fromRow + 1, fromcolumn);
					}
					else {
						pathSquare = board.getSquare(fromRow - 1, fromcolumn);
					}
					if (!pathSquare.isEmpty()) {
						throw new BlockedMoveException();
					}
				}
				break;
			default:// default never called being it uses a peiceType to be called 
				break;
		}
		// pawn & color check for block path 
		if(dest.getPiece() != null){
			if(current == dest.getPiece().getColor()){
				
				if (start.getPiece().getPieceType() != PieceType.PAWN) {
					if (current == PieceColor.White) {
						board.getCoveredWhite()[toRow][toColumn] = 1;
					}
					else {
						board.getCoveredBlack()[toRow][toColumn] = 1;
					}
				}
				throw new BlockedMoveException();
			}
		}
	}
	
	
	
	public void capture (Pieces caught){
		if (caught.getColor()==PieceColor.White){
			whitePlayer.remove(caught);
		
			
		}
		else blackPlayer.remove(caught);
		
		
	}
	
	public Boolean notInCheck(int kingX,int Kingy){
		
		
		for (Pieces temp :whitePlayer)
		if(temp.getCharRepresentation()!=" wK ")	
		if( temp.isMoveLegal(kingX,Kingy)){
			
			return true;}
		
		return false;
		
		
	}
	
	
	
	/**
	 * getter method returns boolean for end of game 
	 * @return boolean 
	 */
	public static boolean isEnd() {
		return end;
	}
	
	/**
	 * getter that returns the status of check mate 
	 * @return boolean that rep check 
	 */
	public static boolean getCheckStatus() {
		return check;
	}
	
	
	
}
