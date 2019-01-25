package slay.source;

/**
 * This class defines the basic behavior to a tree
 * this class is immutable
 * @author Jacob Abkes
 *
 */
public class Tree extends Pawn
{
	
	private boolean isCoastal;
	/**
	 * This is the default constructor for a tree,
	 * this will set it's super's hex to the input, and set it's coastal status
	 * 
	 * we only allow trees to be created with a hex, because the tree
	 * will never move or be placed again. It's first position will be the only
	 * way it can be created. If the input is null, that we means there is no hex there anyway
	 * 
	 * It will throw an IllegalArgumentException if the input is null
	 */
	public Tree(Hex input, boolean isCoastal) 
	{
		super();
		if(input == null) throw new IllegalArgumentException();
		this.isCoastal = isCoastal;
		super.setHex(input);
		super.setDef(0);
		super.setCanMove(false);
	}
	
	/**
	 * This will let us know if the tree is a coast tree
	 * @return
	 */
	public boolean isCoastal() 
	{
		return isCoastal;
	}
	
}
