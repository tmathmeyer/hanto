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

/**
 * 
 * @author otnah
 *
 *
 * A factory for converting piece types to move validators
 */
public class PieceMoveValidatorFactory
{
	/**
	 * 
	 * @param type the piece type to which a validator should be gotten
	 * @return the move validator for that type
	 */
	public static PieceMoveValidator getMoveValidator(HantoGameID game, HantoPieceType type)
	{
		switch(type)
		{
			case BUTTERFLY:
				return new ButterflyMoveValidator();
			case SPARROW:
				return new SparrowMoveValidator(game);
			case CRAB:
				return new CrabMoveValidator();
			default:
				break;
		}
		
		throw new IllegalArgumentException("bad type");
	}
}
