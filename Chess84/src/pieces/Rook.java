package pieces;

import game.Location;

public class Rook extends Pieces{
	
	public Rook(PieceColor color, String[] position){
		super(color,position);
	}

	public Rook(PieceColor color, Location location){
		super(color,location);
		
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
	public boolean isMoveLegal() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public String toString() {
		return getCharRepresentation();
	}

}
