package slay.source;

public class Peasant extends Pawn
{
	
	public Peasant(int ownedBy) 
	{
		super();
		super.setDef(1);
		super.setOwner(ownedBy);
		super.setCanMove(true);
	}
	
	
}
