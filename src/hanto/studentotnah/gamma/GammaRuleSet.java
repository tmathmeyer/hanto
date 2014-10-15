/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.gamma;

import hanto.studentotnah.common.pieces.moves.ButterflyMoveValidator;
import hanto.studentotnah.common.pieces.moves.CrabMoveValidator;
import hanto.studentotnah.common.pieces.moves.HorseMoveValidator;
import hanto.studentotnah.common.pieces.moves.PieceMoveValidator;
import hanto.studentotnah.common.pieces.moves.PieceRuleSet;

/**
 * 
 * @author otnah
 *
 */
public class GammaRuleSet implements PieceRuleSet
{

	@Override
	public PieceMoveValidator makeButterflyMoveValidator()
	{
		return new ButterflyMoveValidator();
	}
	
	@Override
	public PieceMoveValidator makeSparrowMoveValidator()
	{
		return new GammaSparrowMoveValidator();
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

}
