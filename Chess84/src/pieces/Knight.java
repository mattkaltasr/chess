package pieces;

import game.Location;
import game.Location.*;

public class Knight extends Pieces {
	public Knight(PieceColor color, String[] position){
		super(color,position);
	}

	public Knight(PieceColor color, Location location){
		super(color,location);
		
	}

	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if (this.color==PieceColor.Black)
		return " bN ";
		else 
			return " wN ";
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
