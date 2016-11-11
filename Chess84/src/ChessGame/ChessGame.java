package ChessGame;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Board;
import game.TestBoard;
import gamePieces.IllegalMoveException;
import gamePieces.PieceColor;

/**
 * @author matt kalita and Yigit Gungor 
 *
 */
public class ChessGame {
	
	static   Boolean resign= false;
	
	
	
	
	
	
	
	
	/**
	 * Main chess controller using a while loop and a try catch to catch exceptions 
	 * @param args
	 * @throws IllegalMoveException
	 */
	public static void main(String[] args)throws IllegalMoveException {
		   
		 Boolean player=true;//set for white if true 
		 Scanner input =new Scanner(System.in);
		 PieceColor gameColor;
		
		
			
			
			//sets up game board for game play 
		Board newGame= new Board();
		newGame.initialize();
		newGame.initializePieceCounts();
		newGame.printBoard();
		
		
		 @SuppressWarnings("unused")
		int gameCounter =0;
		 
		 
		 
		while((TestBoard.getLastPawnMoveOrCapture()<50)&&!newGame.draw()&&!TestBoard.isEnd()){
			
			gameCounter++;
			
			if(player){
				gameColor=PieceColor.White;
				System.out.print("\n White's Turn ");}//endif white 
			else{ gameColor=PieceColor.Black;
			System.out.print("\n Black's Turn ");
				
			}//end else 
			
			try{
				
				TestBoard.updateProtectedSquares(newGame);
				
				String gameMove=input.nextLine();
				
				if(gameMove.equalsIgnoreCase("resign")){
					if(gameColor==PieceColor.Black){
						System.out.print("White Player  Wins!! ");
					}//endif black 
					else{
						System.out.print("Black Player Wins!! ");
					}//end else
					break;
				}//end gamemove test 
				
				
				
				gameMove=gameMove.trim();//cuts down whitespace for begining and end of string 
				
				if(gameMove.length()==7) {
					TestBoard.promotePawn(gameMove,newGame,gameColor);}
				else { TestBoard.movePiece(gameMove,newGame,gameColor);
					
				}//end if 7 game move 
				
				
				
			}//end try 
			
			catch (InCheckException e){
				gameCounter--;
				System.out.println("Please try again ,cant do that move");
				continue;}
			catch(ImproperColorException e){
				gameCounter--;
				System.out.println("Please try again ,cant do that move");
				continue;}
		catch (BlockedMoveException e){
			gameCounter--;
			System.out.println("Please try again ,cant do that move");
			continue;}
			catch(IllegalMoveException e){
				gameCounter--;
				System.out.println("Please try again ,cant do that move");
				continue;}
			//catch anything else not planned for 
			catch (Exception e){
				
				System.out.println("\nNo Moves ");
				gameCounter--;
				continue;
				}
			
			System.out.println();
			newGame.printBoard();
			TestBoard.updateProtectedSquares(newGame);
			TestBoard.inCheck(newGame,gameColor);
			if (TestBoard.getCheckStatus()){System.out.println(" Check ");}
			
			if(player)
				player=false;
			else
				player=true;
			
				
			
			
			
			
		}//end of while
		
		input.close();
	}//end of main 

}//end of class 
