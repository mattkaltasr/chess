package pieces;

import java.awt.Point;

public class Knight extends Pieces {
	Point current;
	
	
	public Knight(PieceColor color, String[] position){
		super(color,position);
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
