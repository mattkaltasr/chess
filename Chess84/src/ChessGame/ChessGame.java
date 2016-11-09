package ChessGame;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Board;
import game.TestBoard;
import gamePieces.IllegalMoveException;
import gamePieces.PieceColor;

public class ChessGame {
	
	static   Boolean resign= false;
	
	
	
	
	
	
	
	
	
	public static void main(String[] args)throws IllegalMoveException {
   
	 Boolean player=true;//set for white if true 
	 Scanner input =new Scanner(System.in);
	 PieceColor gameColor;
	int Gamecounter =0:
	
		
		
		//sets up game board for game play 
	Board newGame= new Board();
	board.initialize();
	board.initializePieceCounts();
	board.printBoard();
	
	while((getLastPawnmoveorcapture()<50)&&!board.draw()&&!TestBoard.isEnd()){
		Gamecounter++;
		
		if(player){
			gameColor=PieceColor.White;
			System.out.print("\n White's Turn ");}//endif white 
		else{ gameColor=PieceColor.Black;
		System.out.print("\n Black's Turn ");
			
		}//end else 
		
		try{
			String gameMove=input.nextLine();
			TestBoard.updateProtectedSquares(newGame);
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
			else { movePiece(gameMove,newBoard,GameColor);
				
			}//end if 7 game move 
			
			
			
		}//end try 
		
		catch (InCheckException e){
			Gamecounter--;
			System.out.println("Please try again ,cant do that move");
			continue;}
		catch(ImproperColorException e){
			Gamecounter--;
			System.out.println("Please try again ,cant do that move");
			continue;}
	catch (BlockedMoveException e){
		Gamecounter--;
		System.out.println("Please try again ,cant do that move");
		continue;}
		catch(IllegalMoveException e){
			Gamecounter--;
			System.out.println("Please try again ,cant do that move");
			continue;}
		//catch anything else not planned for 
		catch (Exception e){
			
			System.out.println("\nNo Moves ");
			
			}
		
		System.out.println();
		TestBoard.updateProtectedSquares(newGame);
		TestBoard.inCheck(newGame,gameColor);
		if (TestBoard.getCheckStatus()){System.out.println(" Check ");}
		
		
		
			
		
		
		
		
	}//end of while
	
	
	}//end of main 
	
	
	

}
