package slay.source;

/**
 * The purpose of this class is to describe the behavior of any hex in 
 * Sean O'Connor's game Slay
 * 
 * Any hex will have some kind of information about it
 * a hex may be owned by one of the colors green, light green, dark green, brown, yellow and gold(a default state of
 * none is also possible)
 * it may have a piece on it, this could include a pawn, a tree, a castle, a town etc.
 * a hex may have up to six different neighbor hexs, which it will hold a reference to
 * 
 * The indices of the neighbor array move clockwise, starting with the north side of the hex
 *    
 *   n__
 * nw/  \ ne
 * sw\__/ se
 *     s
 *    
 *      
 * Neighbor[0] = north 
 * Neighbor[1] = north east
 * Neighbor[2] = south east
 * Neighbor[3] = south
 * Neighbor[4] = south west
 * Neighbor[5] = north west
 * 
 * This object provides both setter and getter methods. Never ever use getter methods 
 * to update object references. This can be changed so we return a copy of the wanted 
 * data field, this prevents any wrongfull access
 * 
 * @author Jacob Abkes
 *
 */
public class Hex 
{

	
	private int ownedBy;
	private Pawn pieceOn;
	private Hex[] neighbor;
	private Edge[] paths;
	/**
	 * This is the default constructor, it sets ownedBy to none, pieceOn to null, and will
	 * initialize the Neighbor array to a size of 6 with each reference being set to null
	 */
	public Hex()
	{
		ownedBy = 0;
		pieceOn = null;
		neighbor = new Hex[6];
		paths = new Edge[6];
	}
	
	/**
	 * This method will update the color who owns this tile
	 * @param set
	 */
	public void setOwner(int set) 
	{
		ownedBy = set;
	}
	
	/**
	 * This method will return what color owns this tile
	 * @return
	 */
	public int getOwner() 
	{
		return ownedBy;
	}
	
	/**
	 * This method will update the current pawn on this tile
	 * @param piece
	 */
	public void setPawn(Pawn piece) 
	{
		pieceOn = piece;
	}
	
	/**
	 * This method will return the current pawn on this tile
	 * @return
	 */
	public Pawn getPawn() 
	{
		return pieceOn;
	}
	
	/**
	 * This method will let us know if there is a pawn currently on this piece
	 * @return
	 */
	public boolean hasPawn() {
		if(pieceOn == null) return false;
		else
			return true;
	}
	
	/**
	 * This method will set the reference to one of it's neighbor
	 * @param neighborIndex
	 * @param hex
	 */
	public void setNeighbor(int neighborIndex, Hex hex) 
	{
		neighbor[neighborIndex] = hex;
		//We need to think about how we can easily update an items neighbor, and easily
		//(based of the index that it is stored in) update it's new Neighbor reference to this hex
		
	}
	
	/**
	 * This method will copy all of the elements from the given array into
	 * the neighbor array
	 * @param Neighbor
	 * 		the argument must have an exact length of 6 and not be null, otherwise this method
	 * 		will throw an IllegalArgumentException
	 */
	public void setAllNeighbors(Hex[] Neighbor) 
	{
		if(Neighbor == null || Neighbor.length != 6) 
		{
			throw new IllegalArgumentException("Wrong length input or the input was a null reference");
		}
		for(int i=0; i<Neighbor.length; i++) 
			this.neighbor[i] = Neighbor[i];
	}
	
	/**
	 * This method will return the reference to some neighbor 
	 * @param neighborIndex
	 * @return
	 */
	public Hex getNeighbor(int neighborIndex) 
	{
		return neighbor[neighborIndex];
	}
	
	/**
	 * This method will return the reference to the current Neighbor array
	 * @return
	 */
	public Hex[] getAllNeighbors() 
	{
		return neighbor;
	}
	
	/**
	 * This method will return whether or not there is a neighbor in that space
	 * @param neighborIndex
	 * @return
	 */
	 public boolean hasNeighbor(int neighborIndex) {
		 if(neighbor[neighborIndex] == null) return false;
		 else
			 return true;
	 }
	
	 
	 public boolean hasCoast() 
	 {
		 for(int i = 0; i < neighbor.length; i++) 
		 {
			 if(neighbor[i] == null)
				 return true;
			 
		 }
		 return false;
	 }
	


}
