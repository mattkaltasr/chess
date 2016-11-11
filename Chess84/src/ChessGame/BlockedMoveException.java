package ChessGame;

/**
 * @author matt kalita and Yigit Gungor 
 *
 */
public class BlockedMoveException  extends Exception{
	
	
	/**
	 * constructor for exception 
	 */
	public BlockedMoveException(){
		super();
	}
	
	
	
	public BlockedMoveException(String message){
		super(message);
	}

}