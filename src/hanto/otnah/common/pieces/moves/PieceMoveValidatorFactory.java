package hanto.otnah.common.pieces.moves;

import hanto.common.HantoPieceType;

public class PieceMoveValidatorFactory
{
	public static PieceMoveValidator getMoveValidator(HantoPieceType type)
	{
		switch(type)
		{
			case BUTTERFLY:
				return new ButterflyMoveValidator();
			case CRAB:
				break;
			case CRANE:
				break;
			case DOVE:
				break;
			case HORSE:
				break;
			case SPARROW:
				return new SparrowMoveValidator();
			default:
				break;
		}
		
		throw new IllegalArgumentException("bad type");
	}
}
