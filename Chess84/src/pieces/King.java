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
	public King(PieceColor color, Location location,Point start){
		super(color,location);
		counter=0;
		current=start;
	}
	
	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if (this.color==PieceColor.Black)
		return " bK ";
		else 
			return " wK ";
	}
	public void setPoint(Point temp){
		this.current=temp;
	}
	public Point getPoint(){
		return current;
	}
   public int getX(){
	   return (int)this.current.getX();
   }
   
   public int getY(){
	   return (int)this.current.getY();
   }
	@Override
	public void Move(Point destination)  throws IllegalMoveException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMoveLegal(int x,int y) {
		//check all single moves of a king 
		if((Math.abs(getX()-x)==1&&getY()-y==0)||( Math.abs(getY()-y)==1&&getX()-x==0)||(Math.abs(getY()-x)==Math.abs(getX()-y)
				//checks that diagonal is only one square 
				&&Math.abs(getX()-x)==1))
			return true;
		// moves more than one space
		else
		return false;
	}

	
	public String toString() {
		return getCharRepresentation();
	}
}
