package slay.source;

import java.util.LinkedList;

public class City extends Pawn
{
	private LinkedList<Hex> territories;
	public City(int ownedBy, LinkedList<Hex> terr, Hex loca) 
	{
		super();
		super.setDef(1);
		super.setOwner(ownedBy);
		super.setHex(loca);
	}
}
