package gamePieces;
// to use to check two parameters 
import java.util.function.BiFunction;

/**
 * @author matt kalita and Yigit Gungor
 *
 */
public abstract class Pieces {
	
	
	private int row;
	private int column;
	private PieceColor color;
	private PieceType piece;
	private boolean moved;
	

	
	
	
	
	/**
	 * for instantiation of below values 
	 * @param row  row of the piece 
	 * @param column column of the piece 
	 * @param piece type of the piece from enum
	 * 
	 * @param color  color of the piece from enum 
	 */
	
	public Pieces(int row, int column, PieceType piece, PieceColor color){
		this.piece = piece;
		this.row = row;
		this.column = column;
		this.color = color;
		this.moved = false;
	}
	
	
	/**
	 *  superclass move method has bifunction Lambda to return boolean  
	 * @param row
	 * @param column
	 * @param canMove
	 * @throws IllegalMoveException
	 */
	public void movePiece(int row, int column, BiFunction<Integer, Integer, Boolean> canMove) 
			throws IllegalMoveException {
		if(row < 0 || row > 7 || column < 0 || column > 7 || !canMove.apply(row, column)){
			throw new IllegalMoveException();
		}
		if(this.row == row && this.column == column){
			throw new IllegalMoveException();
		}
		this.row = row;
		this.column = column;
		this.moved = true;
	}
	
	/**
	 * checks if move is legal 
	 * @param row
	 * @param col
	 * @throws IllegalMoveException
	 */
	public void isMoveLegal(int row, int col) throws IllegalMoveException {
		if(row < 0 || row > 7 || col < 0 || col > 7){
			throw new IllegalMoveException();
		}
	}
	
	/**
	 *  actual move of the piece on the board 
	 *  sets new parameters for object 
	 * @param temprow
	 * @param tempcolumn
	 * @param hasMoved
	 * @throws IllegalMoveException
	 */
	
	public void actualMove(int temprow, int tempcolumn, boolean hasMoved) throws IllegalMoveException {
		if(temprow < 0 || temprow > 7 || column < 0 || column > 7){
			throw new IllegalMoveException();
		}
		this.row = temprow;
		this.column = tempcolumn;
		this.moved = hasMoved;
	}
	
	/**
	 * abstract method for pieces to implement to move the piece
	 * @param row
	 * @param col
	 * @throws IllegalMoveException
	 */
	public abstract void move(int row, int col) throws IllegalMoveException;

	
	
	/**
	 * returns in rep of row 
	 * @return int 
	 */
	public int getRow(){
		return this.row;
	}
	/**
	 * returns int rep of column 
	 * @return int 
	 */
	public int getCol(){
		return this.column;
	}
	/**
	 * method to return enum representaion of piece 
	 * @return PieceColor 
	 */
	public PieceColor getColor(){
		return this.color;
	}
	
	/**
	 * returns representation of piece 
	 * @return
	 */
	public PieceType getPieceType() {
		return piece;
	}
	/**
	 * gets boolean of movement 
	 * @return boolean 
	 */
	
	public boolean hasMoved(){
		return this.moved;
	}
	
	
	/**  abstract method to set the boolean 
	 * 
	 */
	
	
	public void setMoved() {
		moved = true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
