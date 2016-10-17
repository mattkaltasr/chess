package pieces;

import game.Location;

public class Pawn extends Pieces{
	
	public Pawn(PieceColor color, String[] position){
		super(color,position);
	}

	public Pawn(PieceColor color, Location location){
		super(color,location);
		
	}

	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if(this.color==PieceColor.Black)
		return " bp ";
		else 
			return" wp ";
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
