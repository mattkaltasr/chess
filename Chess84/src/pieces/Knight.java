package pieces;


import java.awt.Point;

import game.Location;
import game.Location.*;


public class Knight extends Pieces {
	public int counter;
	Point current;
	
	public Knight(PieceColor color, String[] position,Point start){
		super(color,position);
		counter=0;
		current=start;
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
	public void Move(Point destination)  throws IllegalMoveException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMoveLegal(int x,int y) {
		// use pythagoras to ensure that a move makes a right-angled
	    // triangle move with sides of 1 and 2. 1-squared + 2 squared is 5.
		int deltar=(int) ((this.current.getX())-x);
		int deltac=(int)((this.current.getY())-y);
		return 5==deltar*deltar+deltac*deltac;
	}

	
	
	public String toString() {
		return getCharRepresentation();
	}
}
