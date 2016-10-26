package pieces;

public class Rook extends Pieces{
	
	public Rook(PieceColor color, String[] position){
		super(color,position);
	}

	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if(this.color==PieceColor.Black)
		return " bR ";
		else
			return" wR ";
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
