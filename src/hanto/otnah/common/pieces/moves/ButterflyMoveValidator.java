package hanto.otnah.common.pieces.moves;

import hanto.common.HantoPieceType;
import hanto.otnah.common.Position;


public class ButterflyMoveValidator extends PieceMoveValidator
{
	@Override
	public boolean isValidMove(Position to, Position from)
	{
		int moveDistance = from.getDistanceTo(to);
		boolean result = isLocationUnoccupied(to);
		if (moveDistance == 1)
		{
			// the move is on the board
			result &= (isWalkingBlocked(to, from) &&
					   isValidNewPlaceLocation(to, null) &&
					   isGraphContinuityPreservedAfter(from, to));
					   
		}
		else if (moveDistance == 0)
		{
			// the move is onto the board from the player's inventory
			
			result &= (hasPieceInInventory(HantoPieceType.BUTTERFLY) &&
					   checkButterflyLegality(HantoPieceType.BUTTERFLY, 4) &&
					   isValidNewPlaceLocation(to, currentPlayerColor()));
		}
		return result;
	}
}
