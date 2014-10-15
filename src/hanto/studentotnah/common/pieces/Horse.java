/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.common.pieces;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentotnah.common.HantoTile;

/**
 * 
 * @author otnah
 *
 */
public class Horse extends HantoTile
{

	private final HantoPlayerColor myColor;
	
	/**
	 * The default constructor
	 * @param hpc the color of this piece
	 */
	public Horse(HantoPlayerColor hpc)
	{
		myColor = hpc;
	}
	
	@Override
	public HantoPlayerColor getColor() {
		return myColor;
	}

	@Override
	public HantoPieceType getType() {
		return HantoPieceType.HORSE;
	}

}
