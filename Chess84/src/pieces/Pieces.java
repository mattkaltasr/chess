package pieces;

import game.Location;
import game.Location.*;

public abstract class Pieces {
	PieceColor color;
	String position_x;
	String position_y;
	public Location location;
	
	public Pieces(PieceColor Color, String[] position){
		this.color=Color;
		this.position_x = position[0];
		this.position_y = position[1];
	}
	public Pieces(PieceColor Color, Location location){
		this.color = Color;
		location.assignPiece(this);
		this.location = location;
	}
	
	public abstract String getCharRepresentation();
	
	public  abstract void Move();
	// method needed for piece to move 

	public abstract boolean isMoveLegal();
	
	public PieceColor getColor (){
		return color;
	}
	public String[] getPosition (){
		String[] position = {position_x,position_y};
		return position;
	}
	public String getPosition_x (){
		return position_x;
	}
	public String getPosition_y (){
		return position_y;
	}
	public void setPosition (String[] position){
		this.position_x = position[0];
		this.position_y = position[1];
	}
	
	public void setColor(PieceColor color){
		this.color=color;
	}
}
