package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
	
	public static void main(String args[]) throws IOException{
		Board b = new Board();
		
		while(true){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();
		
		String[] change = input.split(" ");
		b.changePosition(b.positionLettertoInt(change[0])
						,Integer.parseInt(change[1]) -1
						,b.positionLettertoInt(change[2])
						,Integer.parseInt(change[3]) -1);		
		
		}
	}
	

}
