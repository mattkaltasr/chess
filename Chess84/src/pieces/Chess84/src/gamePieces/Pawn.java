package gamePieces;



/**
 * @author matt kalita and Yigit Gungor
 *
 */
public class Pawn extends Pieces {

	
	/**
	 * Pawns Constructor instantiates Pawns fields 
	 * @param row  used for pawns field 
	 * @param col used for pawns initial position 
	 * @param color sets PieceColor enum for game play 
	 */
	
	public Pawn(int row, int col, PieceColor color){
		super(row, col, PieceType.PAWN, color);
	}

	
	
	
	
	
	
	
	/**
	 * move method for pawn asumes that all moves are legal if not blocked and not same color 
	 * @param row used to move path 
	 * @param column used for new path 
	 * @throws IllegalMoveException used if cant move due to capture not possible or check or blocked path 
	 */
	@Override
	public void move(int row, int column) throws IllegalMoveException {
		// TODO Auto-generated method stub
		
		//assumes that a pawn can move in diagonal for the captures 
		
		super.movePiece(row, column, (r, c) -> {
		if(getColor() == PieceColor.Black){
			if(r < getRow()){
				return false;
			}
			if(r == getRow() + 1 && Math.abs(c - getCol()) <= 1){
				return true;
			}
			if(!hasMoved() && r == getRow() + 2 && c == getCol()){
				return true;
			}
			return false;
		} else {
			if(r > getRow()){
				return false;
			}
			if(r == getRow() - 1 && Math.abs(c - getCol()) <= 1){
				return true;
			}
			if(!hasMoved() && r == getRow() - 2 && c == getCol()){
				return true;
			}
			return false;
		}
	});
}
	
	
	/**
	 * override of objects toString method
	 * @return String   returns a string of Pawns Char rep 
	 */
	public String toString() {
		char colorCharr;
		if (getColor() == PieceColor.White) {
			colorCharr = 'w';		}
		else {			colorCharr = 'b';		}
		return colorCharr + "P";
	}
	
	
	
}
