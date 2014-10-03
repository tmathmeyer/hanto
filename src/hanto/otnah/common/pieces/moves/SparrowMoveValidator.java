/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common.pieces.moves;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.otnah.common.Position;

/**
 * 
 * @author otnah
 *
 * a move validator for sparrows
 */
public class SparrowMoveValidator extends PieceMoveValidator
{
	private HantoGameID version;
	
	public SparrowMoveValidator(HantoGameID version)
	{
		this.version = version;
	}
	
	@Override
	public boolean isValidMove(Position to, Position from)
	{
		switch(version)
		{
			case DELTA_HANTO:
				return deltaSparrowValidator(to, from);
			default:
				return defaultSparrowValidator(to, from);
		}
	}
	
	private boolean deltaSparrowValidator(Position to, Position from)
	{
		boolean result = isLocationUnoccupied(to) &&
		         isPieceAtPositionCorrectType(from, HantoPieceType.SPARROW);;
		int moveDistance = from.getDistanceTo(to);
		
		if(moveDistance == 0)
		{
			return result && hasPieceInInventory(HantoPieceType.SPARROW) &&
					checkButterflyLegality(HantoPieceType.BUTTERFLY, 4) &&
					isValidNewPlaceLocation(to, currentPlayerColor());
		}
		return result && isGraphContinuityPreservedAfter(from, to) &&
				isValidNewPlaceLocation(to, null);
	}
	
	private boolean defaultSparrowValidator(Position to, Position from)
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
