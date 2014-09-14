package hanto.otnah.common;

import hanto.common.HantoCoordinate;

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
	public int getDistanceTo(HantoCoordinate other)
	{
		return 0;
	}

}
