package ChessGame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.TestBoard;
import gamePieces.IllegalMoveException;

/**
 * @author matt kalita and Yigit Gungor
 *
 */
public class GameMoves extends ChessGame {

	
	
	
	/**
	 * accepts string rep of move and using refex which matches string comparisond returns the int value of the move 
	 * @param temp
	 * @return
	 * @throws Exception
	 * @throws IllegalMoveException
	 */
	public static int[] convertMove(String temp) throws Exception, IllegalMoveException{
		int[] result = new int[4];
		if (resign && temp.equalsIgnoreCase("draw")) {
			TestBoard.setEnd();
			System.out.println("Draw.");
			throw new Exception();
		}
		if (temp.length() == 11) {
			String quit = temp.substring(6);
			if (quit.equals("draw?")) {
				resign = true;
			}
		}
		else if(temp.length() != 5){
			//System.out.println("here less than 5 ");
			throw new IllegalMoveException();
		}
		for(String s: temp.split(" ")){
			if(s.length() != 2 && !s.equals("draw?")){
				//System.out.println("here less than 2 ");
				throw new IllegalMoveException();
			}
		}
		//sets pattern in for matching 
		String regex = "[a-h][1-8]";
		//compiles the squars in a pattern 
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(temp);
		for(int i = 0; i <= 2; i+=2) {
			if(m.find()) {
				String output = m.group(0);
				//finds output saves as int[]
				result[i] = 8 - Integer.parseInt(output.charAt(1) + "");
				result[i + 1] = output.charAt(0) - 'a';
			} else {
				throw new IllegalMoveException();
			}
		}// returns int [] of the move to check path 
		return result;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
