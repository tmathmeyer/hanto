package hanto.otnah.common.pieces.moves;

import hanto.common.HantoPieceType;
import hanto.otnah.common.Position;
import hanto.otnah.common.util.HexUtil;

public class HorseMoveValidator extends PieceMoveValidator {

	@Override
	protected boolean isValidMove(Position to, Position from)
	{
		boolean result = isLocationUnoccupied(to) &&
		         isPieceAtPositionCorrectType(from, HantoPieceType.HORSE);
		int moveDistance = from.getDistanceTo(to);
		
		if (moveDistance == 0)
		{
			return result && hasPieceInInventory(HantoPieceType.HORSE) &&
					checkButterflyLegality(HantoPieceType.HORSE, 4) &&
					isValidNewPlaceLocation(to, currentPlayerColor());
		}
		
		if (HexUtil.checkLinearality(to, from))
		{
			return result && isGraphContinuityPreservedAfter(from, to) &&
					isValidNewPlaceLocation(to, null) &&
					areAllPiecesBetweenFilled(to, from);
		}
		
		
		// nothing else matters, the piece is not moving in a straight line
		return false;
	}

}
