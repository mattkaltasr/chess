package pieces;


import java.awt.Point;

import game.Location;


public class Bishop extends Pieces{
	
	Point current;
	
	
	
	public Bishop(PieceColor color, String[] position,Point start){
		super(color,position);

		current=start;

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
	public void Move(Point destination) throws IllegalMoveException{
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public boolean isMoveLegal(int x,int y) {
		//commented out till methods are made 
		//if (pathnotblocked()&&destNotColor())
		//return Math.abs((this.current.getX())-x) == Math.abs((this.current.getY())-y);
		// TODO Auto-generated method stub
		
		return false;
	}
	public String toString() {
		return getCharRepresentation();
	}
}
