package gamePieces;



/**
 * @author matt kalita and Yigit Gungor
 *
 */
public class Queen extends Pieces{
	/**
	 * constructor for queen piece 
	 * @param row sets row 	
	 * @param col  sets column 	
	 * @param color sets pieceColor per the emnum 
	 */

	
	public Queen(int row, int col, PieceColor color){
		super(row, col, PieceType.QUEEN, color);
	}
	
	
	
	/**
	 *  checks to see if legal move 
	 * @param row
	 * @param column
	 * @throws IllegalMoveException
	 */
	public void isMoveLegal(int row, int column) throws IllegalMoveException {
		super.isMoveLegal(row, column);
		if(row != getRow() && column != getCol()){
			int dr = row - getRow();
			if (!(column == getCol() + dr || column == getCol() - dr)) {
				throw new IllegalMoveException();
			}
		}
	}
	
	
	
	
	/**
	 *  method from super abstact overridin 
	 *  @param row gets row int 
	 *  @param column 
	 *  @throws illegalMove Exception if cant move 
	 *  
	 *  
	 *  
	 */

	@Override
	public void move(int row, int column) throws IllegalMoveException {
		// TODO Auto-generated method stub
		super.movePiece(row, column, (r, c) -> {
			if(r != getRow() && c != getCol()){
				int dr = row - getRow();
				return (column == getCol() + dr || column == getCol() - dr);
			}
			return true;
		});
	}
	
	
	
	
	/**
	 * toString override of objects to string 
	 * @return String rep of char values of queen 
	 * 
	 */
	public String toString() {
		char colorCharr;
		if (getColor() == PieceColor.White) {	
			colorCharr = 'w';		}
		else {			colorCharr = 'b';		}
		return colorCharr + "Q";
	}
	
}