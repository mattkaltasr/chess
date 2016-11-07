package pieces;

import java.awt.Point;

import game.Board;
import game.Location;

public class Rook extends Pieces{
	
	public int counter;
	Point current;
	Board board;
	
	public Rook(PieceColor color, String[] position,Point start){
		super(color,position);
		counter=0;
		current=start;
	}

	public Rook(PieceColor color, String[] position,Point start, Board board){
		super(color,position);
		counter=0;
		current=start;
		this.board=board;
	}
	
	public Rook(PieceColor color, Location location,Point start){
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
		return " bR ";
		else
			return" wR ";
	}

	@Override
	public void Move(Point destination) throws IllegalMoveException{
			
		if(destination.x != current.x && destination.y != current.y){
			//Not going straight.
			throw new IllegalMoveException();
		}

		Boolean isPathFree = true;
		if(destination.x != current.x){			
			if(destination.x > current.x)
			{
				for(Pieces piece : board.piecesList)
				{
					int pointOnPathX = Integer.parseInt(piece.position_x);
					int pointOnPathY = Integer.parseInt(piece.position_y);
					
					if(pointOnPathY != current.y){
						continue;
					}
					if(pointOnPathX > current.x && pointOnPathX < destination.x)
					{
						isPathFree = false;
						break;
					}
					
				}
					
			}			
			else if(destination.x < current.x)
			{
				for(Pieces piece : board.piecesList)
				{
					int pointOnPathX = Integer.parseInt(piece.position_x);
					int pointOnPathY = Integer.parseInt(piece.position_y);
					
					if(pointOnPathY != current.y){
						continue;
					}
					if(pointOnPathX < current.x && pointOnPathX > destination.x)
					{
						isPathFree = false;
						break;
					}
					
				}
					
			}
			
		}		
		else if(destination.y != current.y){
			if(destination.y > current.y)
			{
				for(Pieces piece : board.piecesList)
				{
					int pointOnPathX = Integer.parseInt(piece.position_x);
					int pointOnPathY = Integer.parseInt(piece.position_y);
					
					if(pointOnPathX != current.x){
						continue;
					}
					if(pointOnPathY > current.y && pointOnPathY < destination.y)
					{
						isPathFree = false;
						break;
					}
					
				}
					
			}			
			else if(destination.x < current.x)
			{
				for(Pieces piece : board.piecesList)
				{
					int pointOnPathX = Integer.parseInt(piece.position_x);
					int pointOnPathY = Integer.parseInt(piece.position_y);
					
					if(pointOnPathX != current.x){
						continue;
					}
					if(pointOnPathY < current.y && pointOnPathY > destination.y)
					{
						isPathFree = false;
						break;
					}
					
				}
					
			}
		}
		
		if(isPathFree){
			this.current = destination;
			this.counter++;
		}
		
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
