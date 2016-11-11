package ChessGame;

/**
 * @author matt kalita and Yigit Gungor 
 *
 */
public class InCheckException extends Exception {
	
	
	//calls default constructor 
	public InCheckException(){
		super();
			
	}
	
	//calls super message constructor 
	public InCheckException(String message){
	super(message);
	}
}
