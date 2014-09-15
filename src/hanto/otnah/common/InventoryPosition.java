package hanto.otnah.common;

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

}