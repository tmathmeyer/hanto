package hanto.otnah.gamma;

import hanto.common.HantoPieceType;
import hanto.otnah.common.Position;
import hanto.otnah.common.pieces.moves.PieceMoveValidator;

public class GammaSparrowMoveValidator extends PieceMoveValidator
{

	@Override
	protected boolean isValidMove(Position to, Position from)
	{
		int moveDistance = from.getDistanceTo(to);
		boolean result = isLocationUnoccupied(to) &&
		         isPieceAtPositionCorrectType(from, HantoPieceType.SPARROW);
		
		if (moveDistance == 1)
		{
			// the move is on the board
			return result &&
				   isWalkingBlocked(to, from) &&
				   isValidNewPlaceLocation(to, null) &&
				   isGraphContinuityPreservedAfter(from, to);
					   
		}
		else if (moveDistance == 0)
		{
			// the move is onto the board from the player's inventory
			
			return result &&
				   hasPieceInInventory(HantoPieceType.SPARROW) &&
				   checkButterflyLegality(HantoPieceType.BUTTERFLY, 4) &&
				   isValidNewPlaceLocation(to, currentPlayerColor());
		}
		return false;
	}
}
