package game;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ChessGame.BlockedMoveException;
import ChessGame.GameMoves;
import ChessGame.ImproperColorException;
import ChessGame.InCheckException;
import game.Board;
import game.Square;
import gamePieces.*;


/**
 * @author Matt kalita and Yigit Gungor 
 * test board class this class has the move methods of the board pieces 
 */
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
	
	
	/**
	 * method take in a PieceColor and the current board to check for the oppisite color 
	 * @param currentBoard  board 
	 * @param checkColor takes in color param to check 
	 * @return does trial move with current board  returns boolean 
	 */
	public static boolean hasLegalMoves(Board currentBoard, PieceColor checkColor) {
		PieceColor opposite;
		if (checkColor == PieceColor.White) {
			opposite = PieceColor.Black;
		}
		else {
			opposite = PieceColor.White;
		}
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Square temp = currentBoard.getSquare(row, col);
				Pieces playertemp = temp.getPiece();
				//checks to see if square has piece 
				if (playertemp != null && playertemp.getColor() == opposite) {
					for (int newrow = 0; newrow < 8; newrow++) {
						for (int newcol = 0; newcol < 8; newcol++) {
							if (newrow != row || newcol != col) {
								//tries the trial move to check if ok 
								try {
									if(trialMove(row, col, newrow, newcol, currentBoard, opposite)){
										return true;
									}
								} catch (Exception  e) {
									continue;	}	}	}	}		}	}//end of j loop 
		}//end of for loop 
		end = true;
		return false;
	}// end of method 
	
	
	/**
	 * uses the playing board and the players color to det mine the kings activity 
	 * @param current
	 * @param playersColor
	 */
	public static void inCheck(Board current, PieceColor playersColor) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Square playertemp = current.getSquare(row, col);
				if (playertemp.getPiece() != null) {
				//	checks to see if move will result in king capture of opposite players color 
					if (playertemp.getPiece().getPieceType() == PieceType.KING && playertemp.getPiece().getColor() != playersColor) {
						//if king and now to check the color 
						if (playersColor == PieceColor.Black) {
							//if its black king set boolean to true
							if (current.getCoveredBlack()[row][col] == 1) {
								check = true;
								return;			}			}
						else {
							//checks white kning for same 
							if (current.getCoveredWhite()[row][col] == 1) {
								check = true;
								return;				}			}
					}
				}
			}
		}
		check = false;
	}
	
	
	
	//Array takes [ROW][COL] but chess positions are [COL][ROW]
	//So board[y][x] y=a x=2 is actually A2 on the board
	// loops thru and does all checks for moves 
	
	
	/**move piece void method 
	 *  take in the string for the move the current board  and process the moves and checks needed to move the chess piece 
	 * 
	 * @param move
	 * @param board
	 * @param color
	 * @throws Exception
	 * @throws IllegalMoveException
	 */
	public static void movePiece(String move, Board board, PieceColor color) 
			throws Exception,IllegalMoveException {
		PieceColor otherTemp;
		if (color == PieceColor.White) {
			otherTemp = PieceColor.Black;
		}
		else {
			otherTemp = PieceColor.White;
		}
		//sets array of rows and columns
		int[] rowsandColumns = GameMoves.convertMove(move);
		if(color != board.getSquare(rowsandColumns[0], rowsandColumns[1]).getPiece().getColor()){
			throw new ImproperColorException();
		}
		//calls path blocked method for move of piece 
		isPathBlocked(board, rowsandColumns[0], rowsandColumns[1], rowsandColumns[2], rowsandColumns[3]);
		Square playsquare = board.getSquare(rowsandColumns[0], rowsandColumns[1]);
		Square checkSquare = board.getSquare(rowsandColumns[2], rowsandColumns[3]);
		Pieces currentPiece = playsquare.getPiece();
		Pieces tempPiece = checkSquare.getPiece();
		boolean hasMovedTemp = currentPiece.hasMoved();
		
		boolean hasMovedTemp2 = false;
		if(tempPiece != null){
			hasMovedTemp2 = tempPiece.hasMoved();		}
		
		
		//the check for castle move where if king and rook havent moved they can 
		if (currentPiece.getPieceType() == PieceType.KING) {
			Pieces tempRook;
			if (currentPiece.getColor() == PieceColor.White) {
				if (playsquare.toString().equals("[7,4]") && (checkSquare.toString().equals("[7,6]") || checkSquare.toString().equals("[7,2]"))) {
					if (checkSquare.toString().equals("[7,6]")) {
						if (!board.getSquare(checkSquare.getRow(), checkSquare.column() - 1).isEmpty()) {
							throw new BlockedMoveException();
						}
						tempRook = board.getSquare(checkSquare.getRow(), checkSquare.column() + 1).getPiece();
						if (checkCastle(currentPiece, tempRook, board)) {
							currentPiece.move(checkSquare.getRow(), checkSquare.column());
							board.getSquare(checkSquare.getRow(), checkSquare.column()).occupy(currentPiece);
							playsquare.removePiece();
							board.getSquare(checkSquare.getRow(), playsquare.column() + 1).occupy(new Rook(checkSquare.getRow(), playsquare.column() + 1, currentPiece.getColor()));
							board.getSquare(checkSquare.getRow(), checkSquare.column() + 1).removePiece();
							lastPawnMoveOrCapture++;
							return;
						}
						else {
							throw new IllegalMoveException();					}					}
					else if (checkSquare.toString().equals("[7,2]")) {
						if (!board.getSquare(checkSquare.getRow(), checkSquare.column() + 1).isEmpty()) {
							throw new BlockedMoveException();
						}
						tempRook = board.getSquare(checkSquare.getRow(), checkSquare.column() - 2).getPiece();
						if (checkCastle(currentPiece, tempRook, board)) {
							currentPiece.move(checkSquare.getRow(), checkSquare.column());
							board.getSquare(checkSquare.getRow(), checkSquare.column()).occupy(currentPiece);
							playsquare.removePiece();
							board.getSquare(checkSquare.getRow(), playsquare.column() - 1).occupy(new Rook(checkSquare.getRow(), playsquare.column() - 1, currentPiece.getColor()));
							board.getSquare(checkSquare.getRow(), checkSquare.column() - 2).removePiece();
							lastPawnMoveOrCapture++;
							return;
						}	
						else {
							throw new IllegalMoveException();
						}
					}
				}
			}//check below for castle move 
			
			else {
				if (playsquare.toString().equals("[0,4]") && (checkSquare.toString().equals("[0,6]") || checkSquare.toString().equals("[0,2]"))) {
					if (checkSquare.toString().equals("[0,6]")) {
						if (!board.getSquare(checkSquare.getRow(), checkSquare.column() - 1).isEmpty()) {
							throw new BlockedMoveException();
						}
						tempRook = board.getSquare(checkSquare.getRow(), checkSquare.column() + 1).getPiece();
						if (checkCastle(currentPiece, tempRook, board)) {
							currentPiece.move(checkSquare.getRow(), checkSquare.column());
							currentPiece.setMoved();
							board.getSquare(checkSquare.getRow(), checkSquare.column()).occupy(currentPiece);
							playsquare.removePiece();
							board.getSquare(checkSquare.getRow(), playsquare.column() + 1).occupy(new Rook(checkSquare.getRow(), playsquare.column() + 1, currentPiece.getColor()));
							board.getSquare(checkSquare.getRow(), playsquare.column() + 1).getPiece().setMoved();
							board.getSquare(checkSquare.getRow(), checkSquare.column() + 1).removePiece();
							lastPawnMoveOrCapture++;
							return;
						}
						else {
							throw new IllegalMoveException();
						}
					}
					if (checkSquare.toString().equals("[0,2]")) {
						if (!board.getSquare(checkSquare.getRow(), checkSquare.column() + 1).isEmpty()) {
							throw new BlockedMoveException();
						}
						tempRook = board.getSquare(checkSquare.getRow(), checkSquare.column() - 2).getPiece();
						if (checkCastle(currentPiece, tempRook, board)) {
							currentPiece.move(checkSquare.getRow(), checkSquare.column());
							currentPiece.setMoved();
							board.getSquare(checkSquare.getRow(), checkSquare.column()).occupy(currentPiece);
							playsquare.removePiece();
							board.getSquare(checkSquare.getRow(), playsquare.column() - 1).occupy(new Rook(checkSquare.getRow(), playsquare.column() - 1, currentPiece.getColor()));
							board.getSquare(checkSquare.getRow(), playsquare.column() - 1).getPiece().setMoved();
							board.getSquare(checkSquare.getRow(), checkSquare.column() - 2).removePiece();
							lastPawnMoveOrCapture++;
							return;
						}		
						else {
							throw new IllegalMoveException();
						}
					}	
				}
			}
		}
		
		//pawn can only move diagonally if it's capturing 
		if (currentPiece.getPieceType() == PieceType.PAWN) {
			//check for if its capturing)
			if (pawnCapture(currentPiece, board, checkSquare)) {
				Square adj_square;
				if (currentPiece.getColor() == PieceColor.White) {
					adj_square = board.getSquare(checkSquare.getRow() + 1, checkSquare.column());
					board.getBlackCounts().put(PieceType.PAWN, board.getBlackCounts().get(PieceType.PAWN) - 1);
				}
				else {
					adj_square = board.getSquare(checkSquare.getRow() - 1, checkSquare.column());
					board.getWhiteCounts().put(PieceType.PAWN, board.getWhiteCounts().get(PieceType.PAWN) - 1);
				}
				adj_square.removePiece();
			}
			else {
				if (currentPiece.getColor() == PieceColor.White) {
					if (checkSquare != board.getSquare(playsquare.getRow() - 1, playsquare.column())) {
						if (currentPiece.hasMoved()){ 
							if ((!pawnCapture(currentPiece, board, checkSquare) && checkSquare.isEmpty()) || checkSquare.isEmpty() || checkSquare.getPiece().getColor() == PieceColor.White) {
								throw new IllegalMoveException();
							}
						}
						else {
							if (checkSquare != board.getSquare(playsquare.getRow() - 2, playsquare.column()) && (checkSquare.isEmpty() || checkSquare.getPiece().getColor() == PieceColor.White)) {
								throw new IllegalMoveException();
							}
						}
					}
					if (checkSquare == board.getSquare(playsquare.getRow() - 1, playsquare.column())  || checkSquare == board.getSquare(playsquare.getRow() - 2, checkSquare.column())) {
						Pieces blocked = checkSquare.getPiece();
						if (blocked != null) {
							throw new BlockedMoveException();
						}
					}
				}
				else {
					if (checkSquare != board.getSquare(playsquare.getRow() + 1, playsquare.column()) && currentPiece.hasMoved()) {
						if (currentPiece.hasMoved()) {
							if ((!pawnCapture(currentPiece, board, checkSquare) && checkSquare.isEmpty()) || checkSquare.isEmpty() || checkSquare.getPiece().getColor() == PieceColor.Black) {
								throw new IllegalMoveException();
							}
						}
						else {
							if (checkSquare != board.getSquare(playsquare.getRow() + 2, playsquare.column()) && (checkSquare.isEmpty() || checkSquare.getPiece().getColor() == PieceColor.Black)) {
								throw new IllegalMoveException();
							}
						}
					}
					if (checkSquare == board.getSquare(playsquare.getRow() + 1, playsquare.column())  || checkSquare == board.getSquare(playsquare.getRow() + 2, checkSquare.column())) {
						Pieces blocked = checkSquare.getPiece();
						if (blocked != null) {
							throw new BlockedMoveException();
						}
					}
				}
			}
		}
		//places capture piece in temp deducts from piece count and adds to lastpawn variable and then removes captured piece 
		Pieces captured = checkSquare.getPiece();
		if (captured != null || currentPiece.getPieceType() == PieceType.PAWN) {
			if (captured != null) {
				if (captured.getColor() == PieceColor.White) {
					board.getWhiteCounts().put(captured.getPieceType(), board.getWhiteCounts().get(captured.getPieceType()) - 1);
				}
				else {
					board.getBlackCounts().put(captured.getPieceType(), board.getBlackCounts().get(captured.getPieceType()) - 1);
				}
				checkSquare.removePiece();
			}
			lastPawnMoveOrCapture = 0;
		}
		else {
			lastPawnMoveOrCapture++;
		}
		currentPiece.move(checkSquare.getRow(), checkSquare.column());
		updateProtectedSquares(board);
		currentPiece.setMoved();
		checkSquare.occupy(currentPiece);
		playsquare.removePiece();
		if (currentPiece.getColor() == PieceColor.White) {
			white_moves.add(move);
		}
		else {
			black_moves.add(move);
		}
		//Pawn promotion if reached other players side  
		//promotes it to Queen assisted
		//by pawn promotion  below for switch rules 
		if (checkSquare.getPiece().getPieceType() == PieceType.PAWN) {
			if (checkSquare.getPiece().getColor() == PieceColor.White) {
				if (checkSquare.getRow() == 0) {
					checkSquare.removePiece();
					checkSquare.occupy(new Queen(checkSquare.getRow(), checkSquare.column(), color));
					board.getWhiteCounts().put(PieceType.QUEEN, board.getWhiteCounts().get(PieceType.QUEEN) + 1);
					board.getWhiteCounts().put(PieceType.PAWN, board.getWhiteCounts().get(PieceType.PAWN) - 1);
				}
			}
			else {
				if (checkSquare.getRow() == 7) {
					checkSquare.removePiece();
					checkSquare.occupy(new Queen(checkSquare.getRow(), checkSquare.column(), color));
					board.getBlackCounts().put(PieceType.QUEEN, board.getBlackCounts().get(PieceType.QUEEN) + 1);
					board.getBlackCounts().put(PieceType.PAWN, board.getBlackCounts().get(PieceType.PAWN) - 1);
				}
			}
		}
		
		
		//updates all the squares checks for in check the re-updates 
		updateProtectedSquares(board);
		inCheck(board, otherTemp);
		updateProtectedSquares(board);
		if(color == PieceColor.White){
			inCheck(board, PieceColor.Black);
		} else {
			inCheck(board, PieceColor.White);
		}
		if(check){	
			currentPiece.actualMove(rowsandColumns[0], rowsandColumns[1], hasMovedTemp);
			playsquare.occupy(currentPiece);
			checkSquare.removePiece();
			if(tempPiece != null){
				tempPiece.actualMove(rowsandColumns[2], rowsandColumns[3], hasMovedTemp2);
				checkSquare.occupy(tempPiece);
			}
			throw new InCheckException();
		}
		//checks for a  if no legal moves avail for avail players pieces 
		if(!hasLegalMoves(board, color)){
			updateProtectedSquares(board);
			inCheck(board, color);
			System.out.println();
			board.printBoard();
			System.out.println();
			if(check){
				String col = color == PieceColor.Black ? "Black" : "White";
				System.out.println(col + " wins");
			} else {
				System.out.println("Stalemate");
			}
			System.exit(0);
		}
	}
	/**
	 * getter method to return last pawn moved or captured 
	 * @return
	 */
	public static int getLastPawnMoveOrCapture() {
		return lastPawnMoveOrCapture;
	}
	

	/**updates the current squares 
	 * takes in current board to do updating of squares  
	 * 
	 * @param currentBoard
	 */
	public static void updateProtectedSquares(Board currentBoard) {
		currentBoard.resetCoveredSquares();
		for (int row1 = 0; row1 < 8; row1++) {
			for (int col1 = 0; col1 < 8; col1++) {
				Pieces tempPlayer = currentBoard.getSquare(row1, col1).getPiece();
				//only execute if the first square has a piece
				if (tempPlayer != null) {
					//different cases for non-pawns
					if (tempPlayer.getPieceType() != PieceType.PAWN) {
						for (int rowloop = 0; rowloop < 8; rowloop++) {
							for (int colloop = 0; colloop < 8; colloop++) {
								if (rowloop != row1 || colloop != col1) {
									try {
										tempPlayer.isMoveLegal(rowloop, colloop);
										isPathBlocked(currentBoard, row1, col1, rowloop, colloop);
									} catch (Exception  e) {
										continue;
									}
									if (tempPlayer.getColor() == PieceColor.White) {
										currentBoard.getCoveredWhite()[rowloop][colloop] = 1;
									}
									else {
										currentBoard.getCoveredBlack()[rowloop][colloop] = 1;
						}//end if color 
						} //end if x!
							}//end for y
						}//end for x 
					}//end if piece 
			else {
						
						int column = tempPlayer.getCol();
						int row = tempPlayer.getRow();
						if (row != 0 && row != 7) {
							if (column != 0 && column != 7) {
								if (tempPlayer.getColor() == PieceColor.White) {
									currentBoard.getCoveredWhite()[row-1][column+1] = 1;
									currentBoard.getCoveredWhite()[row-1][column-1] = 1;
								}
								else {
									currentBoard.getCoveredBlack()[row+1][column+1] = 1;
									currentBoard.getCoveredBlack()[row+1][column-1] = 1;
								}
							}
							else {
								if (column == 0) {
									if (tempPlayer.getColor() == PieceColor.White) {
										currentBoard.getCoveredWhite()[row-1][column+1] = 1;
									}
									else {
										currentBoard.getCoveredBlack()[row+1][column+1] = 1;
									}
								}
								else if (column == 7) {
									if (tempPlayer.getColor() == PieceColor.White) {
										currentBoard.getCoveredWhite()[row-1][column-1] = 1;
									}
									else {
										currentBoard.getCoveredBlack()[row+1][column-1] = 1;
									}//end if pcolor 
								}//end row(column==0
							}//end if column
						}//end if row
						
					}//end if peice pawn 
				}//end if pnull
			}//end for j 
		}//end nested for i
	}//method end 
	
	
	/**
	 * Mutator void method that sets the boolean to true if ended 
	 */
	public static void setEnd() {
		end = true;
	}
	
	/**method checks rook and king position for ability to castle 
	 * can only be done if neither has move ie original position 
	 * 
	 * @param piece1
	 * @param piece2
	 * @param currentBoard
	 * @return boolean if it can be done or not 
	 * @throws BlockedMoveException throws this if cant do move due to blocked square 
	 */
	private static boolean checkCastle(Pieces piece1, Pieces piece2, Board currentBoard) throws BlockedMoveException {
		if (piece1.getPieceType() != PieceType.KING || (piece2 == null || piece2.getPieceType() != PieceType.ROOK)) {
			return false;
		}
		else if (piece1.getColor() != piece2.getColor()) {
			return false;
		}
		else if (piece1.hasMoved() || piece2.hasMoved()) {
			return false;
		}
		else if (check) {
			return false;
		}
		else {
			Square right_side = currentBoard.getSquare(piece1.getRow(), piece1.getCol() + 2);
			Square left_side = currentBoard.getSquare(piece1.getRow(), piece1.getCol() - 2);
			if (!(right_side.isEmpty() && currentBoard.getSquare(right_side.getRow(), right_side.column() - 1).isEmpty()) &&
					!(left_side.isEmpty() && currentBoard.getSquare(left_side.getRow(), left_side.column() + 1).isEmpty())) {
				return false;
			}
			//can't castle through or into check
			//method checks movement to see if in check or not returns false if move puts in check 
			else {
				boolean safeOnRight = true;
				boolean safeOnLeft = true;
				if (piece1.getColor() == PieceColor.White) {
					if (currentBoard.getCoveredBlack()[right_side.getRow()][right_side.column()] == 1 ||
							currentBoard.getCoveredBlack()[right_side.getRow()][right_side.column() - 1] == 1) {
						safeOnRight = false;
					}
					if (currentBoard.getCoveredBlack()[left_side.getRow()][left_side.column()] == 1 ||
							currentBoard.getCoveredBlack()[left_side.getRow()][left_side.column() + 1] == 1) {
						safeOnLeft = false;
					}
				}
				else {
					if (currentBoard.getCoveredWhite()[right_side.getRow()][right_side.column()] == 1 ||
							currentBoard.getCoveredWhite()[right_side.getRow()][right_side.column() - 1] == 1) {
						safeOnRight = false;
					}
					if (currentBoard.getCoveredWhite()[left_side.getRow()][left_side.column()] == 1 ||
							currentBoard.getCoveredWhite()[left_side.getRow()][left_side.column() + 1] == 1) {
						safeOnLeft = false;
					}
				}
				if (!safeOnRight && !safeOnLeft) {
					return false;				}
			}
			return true;		}	}//end of method 
	
	
	
	
	/**
	 * method used for definitions of pawn promotion using a simple switch depending on call 
	 * @param move takes in move for promotion 
	 * @param board takes in current board in play 
	 * @param color takes in PieceColor 
	 * @throws Exception throws ImproperColorException if wrong color 
	 * @throws IllegalMoveException default if can't be done 
	 */
	public static void promotePawn(String move, Board board, PieceColor color) throws Exception,IllegalMoveException {
		if(move.charAt(5) != ' '){
			throw new IllegalMoveException();
		}
		String chamgetemp = ("" + move.charAt(6)).toLowerCase(); //character representation of the piece to promote to
		int[] rowsAndColumns =  GameMoves.convertMove(move.substring(0, 5));
		Square start = board.getSquare(rowsAndColumns[0], rowsAndColumns[1]), end = board.getSquare(rowsAndColumns[2], rowsAndColumns[3]);
		if(color != start.getPiece().getColor()){
			throw new ImproperColorException();
		}
		isPathBlocked(board, rowsAndColumns[0], rowsAndColumns[1], rowsAndColumns[2], rowsAndColumns[3]);
		//move piece accordingly
		System.out.println(move);
		movePiece(move.substring(0, 5), board, color);
		Pieces tempPiece;
		end.removePiece();
		
		switch(chamgetemp) {
			
			
			case "n":
				tempPiece = new Knight(rowsAndColumns[2], rowsAndColumns[3], color);
				break;
			case "q":
				tempPiece = new Queen(rowsAndColumns[2], rowsAndColumns[3], color);
				break;
			
			case "b":
				tempPiece = new Bishop(rowsAndColumns[2], rowsAndColumns[3], color);
			
				break;
				
			default:
				throw new IllegalMoveException();
		}
		
		end.occupy(tempPiece);
		lastPawnMoveOrCapture = 0;
		if (color == PieceColor.White) {
			board.getWhiteCounts().put(tempPiece.getPieceType(), board.getWhiteCounts().get(tempPiece.getPieceType()) + 1);
			board.getWhiteCounts().put(PieceType.PAWN, board.getWhiteCounts().get(PieceType.PAWN) - 1);
		}
		else {
			board.getBlackCounts().put(tempPiece.getPieceType(), board.getBlackCounts().get(tempPiece.getPieceType()) + 1);
			board.getBlackCounts().put(PieceType.PAWN, board.getBlackCounts().get(PieceType.PAWN) - 1);
		}
		updateProtectedSquares(board);
	}

	
	
	
	
/**
 * returns a int for a char value 
 * 
 * @param letter
 * @return
 */
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
	
	/**
	 * returns  CHAR FOR int value 
	 * @param number
	 * @return
	 */
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
			//otherwise rows are the same used similar code to rooks move 
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
			
			if (fromRow > toRow) {
			
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
			
			else {
				row = fromRow + 1;
				
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
			
				
				if (fromRow > toRow) {
					
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
				
				else {
					row = fromRow + 1;
					
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
	
	/*public Boolean notInCheck(int kingX,int Kingy){
		
		changed to inCheck 
		
		for (Pieces temp :whitePlayer)
		if(temp.getCharRepresentation()!=" wK ")	
		if( temp.isMoveLegal(kingX,Kingy)){
			
			return true;}
		
		return false;
		
		
	}
	
	*/
	
	/**
	 * getter method returns boolean for end of game 
	 * @return boolean 
	 */
	public static boolean isEnd() {
		return end;
	}
	
	
	
	/**
	 * redo of the move test using a string  but just to check if valid moves not for actual moves used for test 
	 * takes in  position and checks all moves against it no changes are done though 
	 * @param row1
	 * @param col1
	 * @param row2
	 * @param col2
	 * @param board  current board brought in for test 
	 * @param color
	 * @return boolean if moves are available false if not 
	 * @throws Exception same as other BlockMoveexception 
	 * @throws IllegalMoveException
	 */
		private static boolean trialMove(int row1, int col1, int row2, int col2, Board board, PieceColor color) 
				throws Exception,IllegalMoveException{
			PieceColor opposite;
			if (color == PieceColor.White) {
				opposite = PieceColor.Black;
			}
			else {
				opposite = PieceColor.White;
			}
			isPathBlocked(board, row1, col1, row2, col2);
			Square start = board.getSquare(row1, col1);
			Square dest = board.getSquare(row2, col2);
			Pieces player = start.getPiece();
			boolean temp = player.hasMoved();
			Pieces p2Temp = dest.getPiece();
			boolean hasMovedTemp2 = false;
			if(p2Temp != null){
				hasMovedTemp2 = p2Temp.hasMoved();
			}
			
			if (player.getPieceType() == PieceType.KING) {
				Pieces rook_castle;
				if (player.getColor() == PieceColor.White) {
					if (start.toString().equals("[7,4]") && (dest.toString().equals("[7,6]") || dest.toString().equals("[7,2]"))) {
						if (dest.toString().equals("[7,6]")) {
							if (!board.getSquare(dest.getRow(), dest.column() - 1).isEmpty()) {
								throw new BlockedMoveException();
							}
							rook_castle = board.getSquare(dest.getRow(), dest.column() + 1).getPiece();
							if (checkCastle(player, rook_castle, board)) {
								return true;
							}
							else {
								throw new IllegalMoveException();
							}
						}
						else if (dest.toString().equals("[7,2]")) {
							if (!board.getSquare(dest.getRow(), dest.column() + 1).isEmpty()) {
								throw new BlockedMoveException();
							}
							rook_castle = board.getSquare(dest.getRow(), dest.column() - 2).getPiece();
							if (checkCastle(player, rook_castle, board)) {
								return true;
							}	
							else {
								throw new IllegalMoveException();
							}
						}
					}
				}
				
				else {
					if (start.toString().equals("[0,4]") && (dest.toString().equals("[0,6]") || dest.toString().equals("[0,2]"))) {
						if (dest.toString().equals("[0,6]")) {
							if (!board.getSquare(dest.getRow(), dest.column() - 1).isEmpty()) {
								throw new BlockedMoveException();
							}
							rook_castle = board.getSquare(dest.getRow(), dest.column() + 1).getPiece();
							if (checkCastle(player, rook_castle, board)) {
								return true;
							}
							else {
								throw new IllegalMoveException();
							}
						}
						if (dest.toString().equals("[0,2]")) {
							if (!board.getSquare(dest.getRow(), dest.column() + 1).isEmpty()) {
								throw new BlockedMoveException();
							}
							rook_castle = board.getSquare(dest.getRow(), dest.column() - 2).getPiece();
							if (checkCastle(player, rook_castle, board)) {
								return true;
							}		
							else {
								throw new IllegalMoveException();
							}
						}	
					}
				}
			}
			//pawn can only move diagonally if it's capturing
			if (player.getPieceType() == PieceType.PAWN) {
				//check for pawns ability to move diagonal for capture
				if (pawnCapture(player, board, dest)) {
					Square adj_square;
					if (player.getColor() == PieceColor.White) {
						adj_square = board.getSquare(dest.getRow() + 1, dest.column());
						board.getBlackCounts().put(PieceType.PAWN, board.getBlackCounts().get(PieceType.PAWN) - 1);
					}
					else {
						adj_square = board.getSquare(dest.getRow() - 1, dest.column());
						board.getWhiteCounts().put(PieceType.PAWN, board.getWhiteCounts().get(PieceType.PAWN) - 1);
					}
					adj_square.removePiece();
				}
				else {
					if (player.getColor() == PieceColor.White) {
						if (dest != board.getSquare(start.getRow() - 1, start.column())) {
							if (player.hasMoved()){ 
								if ((!pawnCapture(player, board, dest) && dest.isEmpty()) || dest.isEmpty() ||
										dest.getPiece().getColor() == PieceColor.White) {
									throw new IllegalMoveException();
								}
							}
							else {
								if (dest != board.getSquare(start.getRow() - 2, start.column()) &&
										(dest.isEmpty() || dest.getPiece().getColor() == PieceColor.White)) {
									throw new IllegalArgumentException();
								}
							}
						}
						if (dest == board.getSquare(start.getRow() - 1, start.column())  || dest == board.getSquare(start.getRow() - 2, dest.column())) {
							Pieces blocked = dest.getPiece();
							if (blocked != null) {
								throw new BlockedMoveException();
							}
						}
					}
					else {
						if (dest != board.getSquare(start.getRow() + 1, start.column()) && player.hasMoved()) {
							if (player.hasMoved()) {
								if ((!pawnCapture(player, board, dest) && dest.isEmpty()) || dest.isEmpty() ||
										dest.getPiece().getColor() == PieceColor.Black) {
									throw new IllegalMoveException();
								}
							}
							else {
								if (dest != board.getSquare(start.getRow() + 2, start.column()) && (dest.isEmpty() ||
										dest.getPiece().getColor() == PieceColor.Black)) {
									throw new IllegalMoveException();
								}
							}
						}
						if (dest == board.getSquare(start.getRow() + 1, start.column())  || dest == board.getSquare(start.getRow() + 2, dest.column())) {
							Pieces blocked = dest.getPiece();
							if (blocked != null) {
								throw new BlockedMoveException();
							}
						}
					}
				}
			}
			player.move(dest.getRow(), dest.column());
			updateProtectedSquares(board);
			player.setMoved();
			dest.occupy(player);
			start.removePiece();
			//promotes it to Queen by default; additional promotions specified in promotePawn method
			if (dest.getPiece().getPieceType() == PieceType.PAWN) {
				if (dest.getPiece().getColor() == PieceColor.White) {
					if (dest.getRow() == 0) {
						dest.removePiece();
						dest.occupy(new Queen(dest.getRow(), dest.column(), color));
						board.getWhiteCounts().put(PieceType.QUEEN, board.getWhiteCounts().get(PieceType.QUEEN) + 1);
						board.getWhiteCounts().put(PieceType.PAWN, board.getWhiteCounts().get(PieceType.PAWN) - 1);
					}
				}
				else {
					if (dest.getRow() == 7) {
						dest.removePiece();
						dest.occupy(new Queen(dest.getRow(), dest.column(), color));
						board.getBlackCounts().put(PieceType.QUEEN, board.getBlackCounts().get(PieceType.QUEEN) + 1);
						board.getBlackCounts().put(PieceType.PAWN, board.getBlackCounts().get(PieceType.PAWN) - 1);
					}
				}
			}
			updateProtectedSquares(board);
			inCheck(board, opposite);
			player.actualMove(row1, col1, temp);
			start.occupy(player);
			dest.removePiece();
			if(p2Temp != null){
				p2Temp.actualMove(row2, col2, hasMovedTemp2);
				dest.occupy(p2Temp);		
			}
			if(!check){
				return true;
			}
			return false;
		}



		

	
	/**
	 * getter that returns the status of check mate 
	 * @return boolean that rep check 
	 */
	public static boolean getCheckStatus() {
		return check;
	}
	/**
	 * method checks pawns ability to capture other pieces  returns boolean 
	 * @param piece1
	 * @param currentBoard
	 * @param currentSquare
	 * @return  boolean if it can capture true or not false 
	 * @throws Exception
	 * @throws IllegalMoveException throws this if move is no legal 
	 */
	private static boolean pawnCapture(Pieces piece1, Board currentBoard, Square currentSquare) throws Exception, IllegalMoveException {
		if (piece1.getPieceType() != PieceType.PAWN) {
			return false;
		}
		else {
			Square pawnPos = currentBoard.getSquare(piece1.getRow(), piece1.getCol());
			
			Pieces nieghborPawn;
			Square neighbor;
			
			
			
			if (piece1.getColor() == PieceColor.White) {
				if (pawnPos.getRow() != 3) {
					return false;
				}
				neighbor = currentBoard.getSquare(currentSquare.getRow() + 1, currentSquare.column());
				if (neighbor.isEmpty()) {
					return false;
				}
				nieghborPawn = neighbor.getPiece();
				if (nieghborPawn.getPieceType() != PieceType.PAWN) {
					return false;
				}
				if (black_moves.isEmpty()) {
					return false;
				}
				else {
					int[] move =  GameMoves.convertMove(black_moves.get(black_moves.size() - 1)); //retrieve last move black made
					//Blacks last move must meet criteria 
					if (move[0] != 1 || move[1] != currentSquare.column() ||
							move[2] != currentSquare.getRow() + 1 || move[3] != currentSquare.column()) {
						return false;
					}//end black last move 
				}//end black move empty 		
			}//end if piece white 
			//black's move
			else {
				if (pawnPos.getRow() != 4) {
					return false;
				}
				neighbor = currentBoard.getSquare(currentSquare.getRow() - 1, currentSquare.column());
				if (neighbor.isEmpty()) {
					return false;
				}
				nieghborPawn = neighbor.getPiece();
				if (nieghborPawn.getPieceType() != PieceType.PAWN) {
					return false;
				}
				if (white_moves.isEmpty()) {
					return false;
				}
				else {
					int[] white_last_move = GameMoves.convertMove(white_moves.get(white_moves.size() - 1)); //retrieve last move white made
					//last move white made has to be a 2-square pawn move in an adjacent row
					if (white_last_move[0] != 6 || white_last_move[1] != currentSquare.column() ||
							white_last_move[2] != currentSquare.getRow() - 1 || white_last_move[3] != currentSquare.column()) {
						return false;
					}//end if white 
				}//end if white empty 			
			}//end if piece=white 
		}//end if =pawn 
		return true;
	}
	
	
	
	
}
