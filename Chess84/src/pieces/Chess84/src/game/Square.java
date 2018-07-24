package game;
import gamePieces.Pieces;










/**
 * @author matt kalita and Yigit Gungor 
 *
 */
public class Square {
	
	
	
	private char file;  //char rep of row 
	private int row;
	private int column;
	private int color; 
	private boolean squareEmpty;
	private Pieces piece;

	
	
	
	
	/**
	 * Square Constructor receives parameters to instantiate the fields on the game squares 
	 * @param rowFile   for the Char value of row "File" 
	 * @param temprowr
	 * @param tempcolumn
	 * @param tempColor
	 */
	public Square(char rowFile, int temprowr, int tempcolumn, int tempColor) {
		this.row = temprowr;
		this.file = rowFile;
		this.column = tempcolumn;
		this.color = tempColor;
		this.squareEmpty = true;
		this.piece = null;
	}
	/**
	 * methed that returns the boolean of the is empty to see if square is empty 
	 * @return  boolean of the empty param 
	 */
	public boolean isEmpty() {
		return squareEmpty;
	}
	
	/**
	 * returns the int that represents the color on the square decided not to use enum to save on code 
	 * 0 for black, 1 for white
	 * @return int for color 
	 */
	public int getColor() {
		return color;
	}
	
	
	/**
	 * getter method to get row int 
	 * @return in for row 
	 */
	public int getRow() {
		return row;
	}
	
	
	/**
	 * getter method to get piece 
	 * @return pieces object 
	 */
	public Pieces getPiece() {
		return piece;
	}
	
	
	
	
	/**
	 * getter method to return the int for the column variable rep
	 * @return int 
	 */
	public int column() {
		return column;
	}
	
	/**
	 * void mutator method that sets the piece on the square to null 
	 */
	public void removePiece() {
		piece = null;
		squareEmpty = true;
	}
	
	/**
	 * getter method that returns the char rep of the "File"(row)
	 * @return
	 */
	public char getCharRow() {
		return file;
	}
	
	/**
	 * Mutator method assigns a piece object to the current squares field 
	 * @param p
	 */
	public void occupy(Pieces p) {
		piece = p;
		squareEmpty = false;
	}
	
	
	/**
	 * used to print out the char representation of each square on board ## for black and empty for white 
	 * @return String depending on whether 1 or 0 
	 */
	public String display() {
		if (squareEmpty) {
			if (color == 0) {
				return "##";	}
			return "  ";
		}
		return getPiece().toString();
	}
	
	
	
	
	
	/**
	 * method to set row end notation 
	 * @return
	 */
	public String getNotation() {
		return file + "" + (8 - row);
	}
	/**
	 * toString override of objects 
	 * @return String of rep of square 
	 */
	public String toString() {
		return "[" + row + "," + column + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
