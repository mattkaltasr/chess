package gamePieces;
/**
 * @author matt kalita and Yigit Gungor
 *
 */
public class Rook extends Pieces {
	
	/**
	 *  Constructor for Rook 
	 * @param row   instantiates rooks initial row 
	 * @param column instantiates rooks initial column 
	 * @param color  instantiates rooks PieceColor from enum 
	 */
	
	public Rook(int row, int column, PieceColor color){
		super(row, column, PieceType.ROOK, color);
	}

	/**
	 * checks to see if legal move can be made 
	 * @param row
	 * @param col
	 * @throws IllegalMoveException throws this if it is not legal to move 
	 */
	public void isMoveLegal(int row, int col) throws IllegalMoveException {
		super.isMoveLegal(row, col);
		if(row != getRow() && col != getCol()){
			throw new IllegalMoveException();
		}
	}
	
	
	
	
	/**
	 * method to move the rook 
	 * @param row receives the row 
	 * @param column receives the column 
	 * sends the two parm to the super via lambda call of bifunction 
	 * @throws illegalMoveException if move can't be done 
	 */
	@Override
	public void move(int row, int column) throws IllegalMoveException {
		// TODO Auto-generated method stub
		
		super.movePiece(row, column, (r, c) -> {
			if(r != getRow() && c != getCol()){
				return false;
			}
			return true;		});
		
		
		
	}











/**
 * void override of objects toString method
 * @return returns a string representation of the Char for rook 
 */
public String toString() {
	char colorCharr;
	if (getColor() == PieceColor.White) {
		colorCharr = 'w';		}
	else {			colorCharr = 'b';		}
	return colorCharr + "R";
}

}