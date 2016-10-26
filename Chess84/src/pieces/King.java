package pieces;

public class King extends Pieces{
	
	public King(PieceColor color, String[] position){
		super(color,position);
		
	}

	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if (this.color==PieceColor.Black)
		return " bK ";
		else 
			return " wK ";
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
