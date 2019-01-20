package wordsearch.prgm;
/**
 * This is a really basic pair class that I wrote to help manage data
 * for the wordsearch class. This class can only be initialized with
 * int values.
 * @author Jacob Abkes
 *
 */
public class Pair 
{
	private int x;
	private int y;
	
	public Pair(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() 
	{
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/**
	 * We add one to the output of this because this is only used for printing out info,
	 * when we print the info out we want it "readable" to a non-programmer
	 * 
	 * ie we start counting at 1 instead of zero
	 */
	public String toString() 
	{
		String returned = "(";
		return returned + (x+1) + ", "+ (y+1) +")";
	}
}
