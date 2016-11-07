package ChessGame;

public class BlockedMoveException  
extends Exception{
	
	
	
	public BlockedMoveException(){
		super();
	}
	
	public BlockedMoveException(String message){
		super(message);
	}

}