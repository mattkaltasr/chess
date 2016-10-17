package pieces;

public abstract class Pieces {
	PieceColor color;
	String position_x;
	String position_y;
	
	public Pieces(PieceColor Color, String[] position){
		this.color=Color;
		this.position_x = position[0];
		this.position_y = position[1];
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
	public void setPosition (String[] position){
		this.position_x = position[0];
		this.position_y = position[1];
	}
	
	public void setColor(PieceColor color){
		this.color=color;
	}
}
