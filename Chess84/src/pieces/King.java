package pieces;

import java.awt.Point;

import game.Location;

public class King extends Pieces{
	

	public int counter;
	Point current;
	
	public King(PieceColor color, String[] position,Point start){
		super(color,position);
		counter=0;
		current=start;
		
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
	public void Move(Point destination)  throws IllegalMoveException{
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
