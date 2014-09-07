package hanto.otnah.common;

import hanto.common.HantoCoordinate;
/**
 * Does various geometric work for determining distances and
 * position relative to other pieces.
 * 
 */
public interface Position extends HantoCoordinate {
	/**
	 * Gets the distance to another position, as in the number of single moves
	 * to get from current to other.
	 * @param other The other piece to get distance to
	 * @return the distance as an int.
	 */
	public int getDistanceTo(HantoCoordinate other);
}
