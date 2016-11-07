package gamePieces;

/**
 * @author matt kalita and Yigit Gunore
 *
 */
public class King extends Pieces{
	
	/**
	 * constructor for king creates a king instance instantiates the parameters 
	 * @param row  of the king 
	 * @param column column representation of the king 
	 * @param color of the king piece
	 */
	public King(int row, int column, PieceColor color){
		super(row, column, PieceType.KING, color);
	}
	
	/**
	 * check King move to see if it can be done 
	 * @param row
	 * @param column
	 * @throws IllegalMoveException if no good 
	 */
	
	public void checkMove(int row, int column) throws IllegalMoveException {
		super.islegal(row, column);
		if(row < getRow() - 1 || row > getRow() + 1 || column < getCol() - 1 || column > getCol() + 1){
			throw new IllegalMoveException();
		}
	}
	
	
	
	/**
	 * 
	 * method inherited by abstract class 
	 * @param krow kings row 
	 * @param column kings column 
	 * @throws IllegalMoveException
	 * 
	 * 
	 */
	

	@Override
	public void move(int krow, int column) throws IllegalMoveException {
		// TODO Auto-generated method stub
		//Lambda calling super method of move bi-function giving it tow ints and getting two returns to see if it can move 
		super.movePiece(krow, column, (r, c) -> {
			if (r == getRow() && (c == getCol() + 2 || c == getCol() - 2)) {
				if (hasMoved()) {
					return false;
				}
				return true;
			}
			else if(r < getRow() - 1 || r > getRow() + 1 || c < getCol() - 1 || c > getCol() + 1){
				return false;
			}
			return true;
		});
		
	}
	
	
	/**
	 *  toString override of objects method 
	 *  returns the char representation as a string of a king 
	 *  @return String 
	 */
	public String toString() {
		char ColorChar;
		if (getColor() == PieceColor.White) {
			ColorChar = 'w';
		}		else {
			ColorChar = 'b';
		}
		return ColorChar + "K";
	}
	
	

}
