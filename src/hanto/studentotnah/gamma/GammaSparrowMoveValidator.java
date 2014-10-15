/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.gamma;

import hanto.common.HantoPieceType;
import hanto.studentotnah.common.Position;
import hanto.studentotnah.common.pieces.moves.PieceMoveValidator;

/**
 * 
 * @author otnah
 *
 */
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
