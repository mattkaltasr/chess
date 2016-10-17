package pieces;

public class Bishop extends Pieces{
	public Bishop(PieceColor color, String[] position){
		super(color,position);
	}

	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if(this.color==PieceColor.Black)
		return " bB ";
		else 
			return " wB ";
					
	}

	@Override
	public void Move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMoveLegal() {
		// TODO Auto-generated method stub
		return false;
	}
	public String toString() {
		return getCharRepresentation();
	}
}
