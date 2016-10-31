package pieces;

import java.awt.Point;

import game.Location;

public class Rook extends Pieces{
	
	public int counter;
	Point current;
	
	public Rook(PieceColor color, String[] position,Point start){
		super(color,position);
		counter=0;
		current=start;
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
	public boolean isMoveLegal(int x,int y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public String toString() {
		return getCharRepresentation();
	}

}
