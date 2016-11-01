package pieces;

import java.awt.Point;

import game.Location;

public class Queen extends Pieces{
	
	public int counter;
	Point current;
	
	public Queen(PieceColor color, String[] position,Point start){
		super(color,position);
		counter=0;
		current=start;
	}


	public Queen(PieceColor color, Location location){
		super(color,location);
		
	}

	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if(this.color==PieceColor.Black)
		return " bQ ";
		else 
			return " wQ ";
	}

	@Override
	public void Move(Point destination) throws IllegalMoveException{
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
