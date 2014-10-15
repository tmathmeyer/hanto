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

/**
 * 
 * @author otnah
 *
 * a move validator for sparrows
 * 
 * TODO: clean this
 */
public class SparrowMoveValidator extends PieceMoveValidator
{
	
	@Override
	public boolean isValidMove(Position to, Position from)
	{
		boolean result = isLocationUnoccupied(to) &&
		         isPieceAtPositionCorrectType(from, HantoPieceType.SPARROW);
		int moveDistance = from.getDistanceTo(to);
		
		if(moveDistance == 0)
		{
			return result && hasPieceInInventory(HantoPieceType.SPARROW) &&
					checkButterflyLegality(HantoPieceType.SPARROW, 4) &&
					isValidNewPlaceLocation(to, currentPlayerColor());
		}
		return result && moveDistance < 5 && 
				isGraphContinuityPreservedAfter(from, to) &&
				isValidNewPlaceLocation(to, null);
	}

}
