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
	private final PieceRuleSet RULES;
	
	public PieceMoveValidatorFactory()
	{
		this(new PieceRuleSet(){

			@Override
			public PieceMoveValidator makeButterflyMoveValidator()
			{
				return new ButterflyMoveValidator();
			}
			
			@Override
			public PieceMoveValidator makeSparrowMoveValidator()
			{
				return new SparrowMoveValidator();
			}
			
			@Override
			public PieceMoveValidator makeCrabMoveValidator()
			{
				return new CrabMoveValidator();
			}
			
			@Override
			public PieceMoveValidator makeHorseMoveValidator()
			{
				return new HorseMoveValidator();
			}
			
		});
	}
	
	/**
	 * singleton constructor
	 */
	public PieceMoveValidatorFactory(PieceRuleSet rules)
	{
		RULES = rules;
	}
	
	/**
	 * 
	 * @param type the piece type to which a validator should be gotten
	 * @param game the game to switch on
	 * @return the move validator for that type
	 */
	public PieceMoveValidator getMoveValidator(HantoPieceType type)
	{
		switch(type)
		{
			case BUTTERFLY:
				return RULES.makeButterflyMoveValidator();
			case SPARROW:
				return RULES.makeSparrowMoveValidator();
			case CRAB:
				return RULES.makeCrabMoveValidator();
			case HORSE:
				return RULES.makeHorseMoveValidator();
			default:
				break;
		}
		
		throw new IllegalArgumentException("bad type");
	}
	
	
}
