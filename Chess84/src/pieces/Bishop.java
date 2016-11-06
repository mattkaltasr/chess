package pieces;


import java.awt.Point;

import game.Location;


public class Bishop extends Pieces{
	
	Point current;
	
	
	
	public Bishop(PieceColor color, String[] position,Point start){
		super(color,position);

		current=start;

	}

	public Bishop(PieceColor color, Location location,Point start){
		super(color,location);
	     setPoint(start);

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
		
		return Math.abs((this.current.getX())-x) == Math.abs((this.current.getY())-y);
		// TODO Auto-generated method stub
		
		
	}
	public String toString() {
		return getCharRepresentation();
	}
}
