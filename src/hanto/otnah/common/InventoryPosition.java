package hanto.otnah.common;

import hanto.common.HantoCoordinate;
import java.util.HashSet;
import java.util.Collection;

public class InventoryPosition extends Position
{
	/**
	 * default constructor
	 */
	public InventoryPosition()
	{
		// do nothing
	}
	
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
	public Collection<HantoCoordinate> adjacentPositions()
	{
		
		return (Collection<HantoCoordinate>)new HashSet<HantoCoordinate>();
	}

}
