package slay.source;

public class Baron extends Pawn
{
	public Baron(int ownedBy) {
		super();
		super.setDef(4);
		super.setCanMove(true);
		super.setOwner(ownedBy);
	}
}
