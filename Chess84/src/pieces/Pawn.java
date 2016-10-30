package pieces;


import java.awt.Point;

import game.Location;


public class Pawn extends Pieces{
	
	//used to count moves 
	
	public int counter;
	Point current;
	
	public Pawn(PieceColor color, String[] position,Point start){
		super(color,position);
		counter=0;
		current=start;
	}

	public Pawn(PieceColor color, Location location){
		super(color,location);
		
	}

	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if(this.color==PieceColor.Black)

		return " bP ";

	

		else 

			return" wP ";

		

	}

	@Override
	public void Move() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public boolean isMoveLegal(int movex,int movey ) {
		// TODO Auto-generated method stub
		int row=Math.abs(Integer.parseInt(position_y)-movey);
		int column=Math.abs(Integer.parseInt(position_x)-movex);
		if (counter<1)
			return ((row==2)&&(column==0));
		
		return false;
	}
	public String toString() {
		return getCharRepresentation();
	}
}
