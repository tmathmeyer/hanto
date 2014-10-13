/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.beta;

import hanto.common.HantoPieceType;
import hanto.otnah.common.Position;
import hanto.otnah.common.pieces.moves.PieceMoveValidator;
import hanto.otnah.common.pieces.moves.PieceRuleSet;

/**
 * @author otnah
 *
 * defines the custom rules for beta hanto, as they dont conform
 * to the normal ones, in that no pieces can move
 */
public class BetaHantoRules extends PieceMoveValidator implements PieceRuleSet
{
	private HantoPieceType type;
	
	/**
	 *  make a default set of hanto rules where all pieces are butterflies
	 */
	public BetaHantoRules()
	{
		this(HantoPieceType.BUTTERFLY);
	}
	
	/**
	 * make a betahantorules with any piece type
	 * @param hpt the type
	 */
	public BetaHantoRules(HantoPieceType hpt)
	{
		type = hpt;
	}
	
	@Override
	public PieceMoveValidator makeButterflyMoveValidator() {
		return new BetaHantoRules();
	}

	@Override
	public PieceMoveValidator makeSparrowMoveValidator() {
		return new BetaHantoRules(HantoPieceType.SPARROW);
	}

	@Override
	public PieceMoveValidator makeCrabMoveValidator() {
		return new BetaHantoRules(HantoPieceType.CRAB);
	}

	@Override
	public PieceMoveValidator makeHorseMoveValidator() {
		return new BetaHantoRules(HantoPieceType.HORSE);
	}

	@Override
	protected boolean isValidMove(Position to, Position from) {
		Position toPos = Position.asPosition(to);
		boolean movePossible = true;
		if (!hasPieceInInventory(type))
		{
			return false;
		}
		
		movePossible = checkButterflyLegality(type, 3);
		
		return movePossible && (isFirstMove(toPos) || 
				(piecePlaceContinuityCheck(toPos) && isLocationUnoccupied(toPos)));
	}

}
