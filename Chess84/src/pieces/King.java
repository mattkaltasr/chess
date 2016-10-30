package pieces;

import game.Location;

public class King extends Pieces{
	
	public King(PieceColor color, String[] position){
		super(color,position);
		
	}
	public King(PieceColor color, Location location){
		super(color,location);
		
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
