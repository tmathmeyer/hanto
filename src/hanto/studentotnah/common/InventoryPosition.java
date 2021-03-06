/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.common;

import java.util.Collection;
import java.util.Collections;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;

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
	public Collection<Position> adjacentPositions()
	{
		return Collections.emptyList();
	}
	
	@Override
	public HantoTile movePieceFrom(GameState state, HantoPieceType type, Position to)
	{
		return state.getCurrentPlayer().play(type, to);
	}

	@Override
	public boolean hasPieceType(GameState state, HantoPieceType type)
	{
		for(HantoTile tile : state.getCurrentPlayer().getInventory())
		{
			if (tile.getType() == type)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public HantoCoordinate unwrap() {
		return null;
	}
	
	@Override
	public String toString()
	{
		return "the inventory";
	}
}
