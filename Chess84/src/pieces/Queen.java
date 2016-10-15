
public class Queen extends Pieces{
	
	public Queen(PieceColor color){
		super(color);
	}

	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if(this.color==PieceColor.Black)
		return "QBK";
		else 
			return "QWT";
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
