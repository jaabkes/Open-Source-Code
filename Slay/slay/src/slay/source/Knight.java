package slay.source;

public class Knight extends Pawn
{
	public Knight(int ownedBy) 
	{
		super();
		super.setDef(3);
		super.setOwner(ownedBy);
		super.setCanMove(true);
	}
}
