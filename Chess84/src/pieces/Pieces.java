
public abstract class Pieces {
	PieceColor color;
		
	public Pieces(PieceColor Color ){
		this.color=Color;
	}
	public abstract String getCharRepresentation();
	
	public  abstract void Move();
	// method needed for piece to move 

	public abstract boolean isMoveLegal();
	
	public PieceColor getColor (){
		return color;
	}
	
	public void setColor(PieceColor color){
		this.color=color;
	}
}
