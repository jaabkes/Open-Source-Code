package slay.source;

/**
 * This class will outline the basic behavior of any kind of pawn
 * that may occupy a hex.
 * 
 * Any pawn may have a hex associated with it
 * @author Jacob Abkes
 *
 */
public abstract class Pawn 
{
	private Hex hexOn;
	
	private boolean canMove;
	
	private int ownedBy; // each player 1-6 has a number corresponding to them
	
	private int def;
	
	/**
	 * This is the default constructor, it will set hexOn and ownedBy to null
	 * 
	 */
	public Pawn() 
	{
		canMove = false;
		hexOn = null;
		ownedBy = 0;
		def = 0;
	}
	
	public boolean getCanMove() {
		return canMove;
	}
	
	public void setCanMove(boolean input) {
		canMove = input;
	}
	
	public int getDef() {
		return def;
	}
	
	public void setDef(int input) 
	{
		def = input;
	}
	
	/**
	 * This method will update hexOn's reference to the inputed argument
	 * this will subsequently set canMove to false. 
	 * 
	 * If canMove is false then this method will throw an illegal state exception
	 * @param input
	 */
	public void setHex(Hex input) 
	{
		if(!canMove) throw new IllegalStateException("Can't move this piece");
		hexOn = input;
		canMove = false;
	}
	
	/**
	 * This method will return whatever hex this piece is on
	 * (if null this piece should not exist on the board)
	 * @return
	 */
	public Hex getHex() 
	{
		return hexOn;
	}
	
	/**
	 * This method will update the owner of this pawn
	 * @param input
	 */
	public void setOwner(int input) 
	{
		ownedBy = input;
	}
	
	/**
	 * This method will return the owner of this pawn
	 * @return
	 */
	public int getOwner() 
	{
		return ownedBy;
	}
	
	/**
	 * This will compare to pawns to see if which is weaker
	 * @param pwn
	 * @return
	 * 	If this pawn is weaker than pwn returns < 0
	 * 	if the same returns 0
	 * 	else returns > 0
	 */
	public int compareTo(Pawn pwn) 
	{
		if(pwn == null) throw new IllegalArgumentException("Other pawn is null");
		if(this.def < pwn.def) return -1; //if there def is lower
		if(this.def == pwn.def) return 0; //if there def is the same
		return 1; //if there def is greater
	}
}
