package gamePieces;
/**
 * @author matt kalita and Yigit Gungor 
 *
 */
public class Knight extends Pieces{

	/**
	 * Constructor for Knight 
	 * @param row sets parm for Knight 
	 * @param col sets parm for Knight 
	 * 
	 * @param color  sets PieceColor for knight from enum 
	 */
	public Knight(int row, int col, PieceColor color){
		super(row, col, PieceType.KNIGHT, color);
	}

	
	
	
	
	/**
	 * method to move the Knight calls super bifunction for test 
	 * @param row 
	 * @param column 
	 */
	@Override
	public void move(int row, int column) throws IllegalMoveException {
		// TODO Auto-generated method stub
		// checks the kNIGHTS UNIQUE PATTERN of moving 
		super.movePiece(row, column, (r, c) -> {
			if((Math.abs(r - getRow()) == 2 && Math.abs(c - getCol()) == 1) || 
					(Math.abs(r - getRow()) == 1 && Math.abs(c - getCol()) == 2)){
				return true;
			}
			return false;
		});
	}
	
	/**
	 * method to check if move is legal 
	 * @param row
	 * @param column
	 * @throws IllegalMoveException throws this if not 
	 */
	public void isMoveLegal(int row, int column) throws IllegalMoveException {
		super.isMoveLegal(row, column);
		// checks both ays row 2 col 1 or row 1 col 2 squares if not throws exception 
		if(!((Math.abs(row - getRow()) == 2 && Math.abs(column - getCol()) == 1) || 
				(Math.abs(row - getRow()) == 1 && Math.abs(column - getCol()) == 2))){
			throw new IllegalMoveException();
		}
	}
	
	
	/**
	 * objects override of toString method 
	 * @return String returns the Char rep in a string of the Knight 
	 */
	public String toString() {
		char colorCharr;
		if (getColor() == PieceColor.White) {
			colorCharr = 'w';		}
		else {			colorCharr = 'b';		}
		return colorCharr + "N";
	}
	
	
	
	
	
	
}
	