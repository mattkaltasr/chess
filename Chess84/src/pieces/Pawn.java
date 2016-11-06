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

	public Pawn(PieceColor color, Location location,Point start){
		super(color,location);
		setPoint(start);
	}

	@Override
	public String getCharRepresentation() {
		// TODO Auto-generated method stub
		if(this.color==PieceColor.Black)

		return " bP ";

	

		else 

			return" wP ";

		

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
	
	
   // move being done in board class
	
	@Override
	public void Move(Point destination)  throws IllegalMoveException{
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public boolean isMoveLegal(int movex,int movey ) {
	  int tempx=(int) this.current.getX();
	  int tempy=(int) this.current.getY();
		// TODO Auto-generated method stub
		int row=Math.abs(tempy-movey);
		int column=Math.abs(tempx-movex);
		if (counter<1)
			return ((row==2)&&(column==0));
		if (counter>1)
			return ((row==1)&&(column==0));
		//need to check if diagonal if location is opposite color 
		
		return checkDiagnalMove(tempx,tempy, movex, movey);
	}
	public Boolean checkDiagnalMove(int tempx,int tempy,int movex,int movey){
		  
		if (Math.abs(tempx-movex)==1&&Math.abs(tempy-movey)==1)
			return true;
		return false ;
	}
	public String toString() {
		return getCharRepresentation();
	}
}
