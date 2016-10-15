
public class King extends Pieces{
	
	public King(PieceColor color){
		super(color);
		
	}

	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if (this.color==PieceColor.Black)
		return "KBK";
		else 
			return "KWT";
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