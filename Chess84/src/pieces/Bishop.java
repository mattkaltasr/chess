package pieces;

import game.Location;

public class Bishop extends Pieces{
	public Bishop(PieceColor color, String[] position){
		super(color,position);
	}

	public Bishop(PieceColor color, Location location){
		super(color,location);
		
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
