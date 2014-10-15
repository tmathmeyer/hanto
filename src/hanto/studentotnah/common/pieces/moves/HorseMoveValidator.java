/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.common.pieces.moves;

import hanto.common.HantoPieceType;
import hanto.studentotnah.common.Position;
import hanto.studentotnah.common.util.HexUtil;

/**
 * 
 * @author otnah
 *
 */
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
