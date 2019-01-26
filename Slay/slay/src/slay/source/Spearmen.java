package slay.source;

public class Spearmen extends Pawn
{
	public Spearmen(int ownedBy) 
	{
		super();
		super.setOwner(ownedBy);
		super.setDef(2);
		super.setCanMove(true);
	}
}
