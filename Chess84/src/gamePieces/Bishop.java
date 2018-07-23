package gamePieces;

/**
 * @author matt kalita 
 *
 */
public class Bishop extends Pieces {
	
	
	/**
	 * Constructor for Bishop 
	 * @param row receives and instantiate the Bishops field 
	 * @param col receives and instantiate the Bishops field 
	 * @param color receives and instantiate the Bishops field for PieceColor enum 
	 */
	public Bishop(int row, int col, PieceColor color){
		super(row, col, PieceType.BISHOP, color);
	}

	/**
	 * used to check if move is legal 
	 * @param row
	 * @param column
	 * @throws IllegalMoveException if move is not able to be done 
	 * 
	 */
	public void isMoveLegal(int row, int column) throws IllegalMoveException {
		super.isMoveLegal(row, column);
		int dr = row - getRow();
		if (!(column == getCol() + dr || column == getCol() - dr)) {
			throw new IllegalMoveException();
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * method to move the Bishop 
	 * @param row used to call super bifunction 
	 * @param column used to call super bifunction 
	 * @throws IllegalMoveException if move is not valid in super 
	 */
	@Override
	public void move(int row, int column) throws IllegalMoveException {
		// TODO Auto-generated method stub
		super.movePiece(row, column, (r, c) -> {
			int dr = row - getRow();
			return (column == getCol() + dr || column == getCol() - dr);
		});
	}
	
	
	 /**
	  * toString override of objects method 
	  * @return String
	  *  returns Char rep of Bishop in a String 
	  */
		public String toString() {
			char color_letter;
			if (getColor() == PieceColor.White) {
				color_letter = 'w';
			}
			else {color_letter = 'b';
			}
			return color_letter + "B";
		}

}
