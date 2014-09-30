/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;

import java.util.Collections;
import java.util.Collection;

/**
 * 
 * @author otnah
 *
 */
public class InventoryPosition extends Position
{
	@Override
	public int getX()
	{
		throw new UnsupportedOperationException(
				"inventory position has no x coordinate");
	}

	@Override
	public int getY()
	{
		throw new UnsupportedOperationException(
				"inventory position has no y coordinate");
	}

	@Override
	public int getDistanceTo(Position other)
	{
		return 0;
	}

	@Override
	public int distanceFrom(Position other) {
		return -1;
	}
	
	@Override
	public Collection<HantoCoordinate> adjacentCoordinates()
	{
		return Collections.emptyList();
	}
	
	@Override
	public boolean equals(Object other)
	{
		return other instanceof InventoryPosition;
	}
	
	@Override
	public int hashCode()
	{
		return 655678901;
	}
	
	@Override
	public HantoTile removePieceAt(GameState state, HantoPieceType type)
	{
		return state.getCurrentPlayer().play(type);
	}

}
