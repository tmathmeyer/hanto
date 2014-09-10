package hanto.otnah.common;

import hanto.common.HantoCoordinate;
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
}
