/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common.pieces;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoTile;

/**
 * 
 * @author otnah
 *
 *
 *	the butterfly piece type
 */
public class Butterfly extends HantoTile
{

	private final HantoPlayerColor color;
	
	/**
	 * @param myColor the color of the butterfly
	 */
	public Butterfly(HantoPlayerColor myColor)
	{
		color = myColor;
	}
	
	@Override
	public HantoPlayerColor getColor()
	{
		return color;
	}

	@Override
	public HantoPieceType getType()
	{
		return HantoPieceType.BUTTERFLY;
	}
}
