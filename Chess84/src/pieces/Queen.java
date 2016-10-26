package pieces;

public class Queen extends Pieces{
	
	public Queen(PieceColor color, String[] position){
		super(color,position);
	}

	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if(this.color==PieceColor.Black)
		return " bQ ";
		else 
			return " wQ ";
	}

	@Override
	public void Move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMoveLegal(int x,int y) {
		// TODO Auto-generated method stub
		return false;
	}

	public String toString() {
		return getCharRepresentation();
	}
	
}
