package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pieces.IllegalMoveException;

public class Game {
	/*
	public static void main(String args[]) throws IOException, NumberFormatException, IllegalMoveException{
		Board b = new Board();
		
		boolean white = true;
		while(true){	
			if(white)				
				System.out.println("White enter your move: {letternumber(from) letternumber(to)} ex: e3 e4");
			else				
				System.out.println("Black enter your move: {letternumber(from) letternumber(to)} ex: e3 e4");
			
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();
		
		if(input.equalsIgnoreCase("resign")){
			if(white)
				System.out.println("WHITE RESIGNED");
			else
				System.out.println("BLACK RESIGNED");
			return;
		}
		
		String[] change = input.split(" ");
		
		String fromLetter = change[0].substring(0, 1);
	    String fromNumber = change[0].substring(1);

		String toLetter = change[1].substring(0, 1);
	    String toNumber = change[1].substring(1);
		
		b.changePosition(b.positionLettertoInt(fromLetter)
						,Integer.parseInt(fromNumber) -1
						,b.positionLettertoInt(toLetter)
						,Integer.parseInt(toNumber) -1, white);		
		white = !white;
		
		}
	}
	
*/
	
	
	public static void main(String args[]) throws IOException, NumberFormatException, IllegalMoveException{
		TestBoard b = new TestBoard();	
		
		boolean white = true;
		while(true){
			if(white)
				System.out.println("Next move (White): ");
			else
				System.out.println("Next move (Black): ");
			System.out.println("");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine();
			
			if(input.equalsIgnoreCase("resign")){
					System.out.println("RESIGNED");
					return;
			}
			
			String[] change = input.split(" ");
			
			String fromLocation = change[0];
			String toLocation = change[1];
			
			b.changePosition(fromLocation, toLocation, white);
			
			white = !white;
		}
	
	}
	
	
}
