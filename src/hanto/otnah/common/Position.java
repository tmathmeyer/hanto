package hanto.otnah.common;

import hanto.common.HantoCoordinate;
import hanto.otnah.common.util.HexUtil;
/**
 * Does various geometric work for determining distances and
 * position relative to other pieces.
 * 
 */
public abstract class Position implements HantoCoordinate {
	
	/**
	 * Gets the distance to another position, as in the number of single moves
	 * to get from current to other.
	 * @param other The other piece to get distance to
	 * @return the distance as an int.
	 */
	public abstract int getDistanceTo(HantoCoordinate other);
	
	/**
	 * a specific overload for inventory positions,
	 * so that there is no attempt to call getX() or
	 * getY() on one of them. Also do not let it be 
	 * over-written by subtypes
	 * 
	 * @param other another inventoryPosition
	 * @return 0
	 */
	public final int getDistanceTo(InventoryPosition other)
	{
		return 0;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (other == this)
		{
			return true;
		}
		
		if (other instanceof HantoCoordinate)
		{
			HantoCoordinate oth = (HantoCoordinate)other;
			return oth.getX() == getX() && oth.getY() == getY();
		}
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return ((getX() * 373929373) + (getY() * 113137337)) * 1000000007;
	}
	
	/**
	 * use casting or if unable, create a new position
	 * 
	 * @param other a hanto coordinate, which may be a position already
	 * @return a position representing a HantoCoordinate
	 */
	public static Position asPosition(final HantoCoordinate other)
	{
		if (other == null)
		{
			return new InventoryPosition();
		}
		return new Position() {
			
			@Override
			public int getX() {
				return other.getX();
			}

			@Override
			public int getY() {
				return other.getY();
			}

			@Override
			public int getDistanceTo(HantoCoordinate other) {
				return HexUtil.distance(this, other);
			}
		};
	}
}
