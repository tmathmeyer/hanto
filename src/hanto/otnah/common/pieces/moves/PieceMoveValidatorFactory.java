/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/
package hanto.otnah.common.pieces.moves;

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
