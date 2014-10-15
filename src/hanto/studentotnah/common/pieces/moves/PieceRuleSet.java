/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.common.pieces.moves;

/**
 * 
 * @author otnah
 *
 */
public interface PieceRuleSet
{
	/**
	 * @return the butterfly validator
	 */
	PieceMoveValidator makeButterflyMoveValidator();
	
	/**
	 * @return the sparrow validator
	 */
	PieceMoveValidator makeSparrowMoveValidator();
	
	/**
	 * @return the crab validator
	 */
	PieceMoveValidator makeCrabMoveValidator();
	
	/**
	 * @return the horse validator
	 */
	PieceMoveValidator makeHorseMoveValidator();
}
