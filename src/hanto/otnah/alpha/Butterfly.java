/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.alpha;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.Position;

/**
 * 
 * @author otnah
 *
 */
public class Butterfly extends HantoTile
{
	private final HantoPlayerColor playerColor;
	
	/**
	 * @param hpc the player color
	 */
	public Butterfly(HantoPlayerColor hpc)
	{
		playerColor = hpc;
	}
	
	@Override
	public HantoPlayerColor getColor()
	{
		return playerColor;
	}

	@Override
	public HantoPieceType getType()
	{
		return HantoPieceType.BUTTERFLY;
	}

	@Override
	public void move(HantoCoordinate to)
	{
		//TODO: not in alpha
	}

	@Override
	public boolean isValidMove(Position to)
	{
		return false; // the butterfly can't move anywhere
	}

	@Override
	public Collection<HantoCoordinate> getAdjacentPositions()
	{
		return null;
	}

}
