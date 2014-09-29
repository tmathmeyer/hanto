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
import hanto.otnah.common.util.HexUtil;
import hanto.otnah.common.util.graph.Adjacency;

import java.util.Collection;

/**
 * Does various geometric work for determining distances and
 * position relative to other pieces.
 * 
 */
public abstract class Position implements HantoCoordinate, Adjacency<HantoCoordinate>{
	
	/**
	 * Gets the distance to another position, as in the number of single moves
	 * to get from current to other.
	 * @param other The other piece to get distance to
	 * @return the distance as an int.
	 */
	public abstract int getDistanceTo(Position other);
	
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
	 * let dynamic dispatch do its work
	 * @param other the other position
	 * @return the distance between
	 */
	public abstract int distanceFrom(Position other);
	
	/**
	 * use casting or if unable, create a new position
	 * 
	 * @param other a hanto coordinate, which may be a position already
	 * @return a position representing a HantoCoordinate
	 */
	public static Position asPosition(final HantoCoordinate other)
	{
		if (other == null || other instanceof InventoryPosition)
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
			public int getDistanceTo(Position other) {
				return other.distanceFrom(this);
			}

			@Override
			public int distanceFrom(Position other) {
				return HexUtil.distance(this, other);
			}

		};
	}

	/**
	 * 
	 * @param check the piece to check for adjacency
	 * @return whether this piece is adjacent to the provided one
	 */
	public boolean isAdjacentTo(Position check) {
		return check.getDistanceTo(this) == 1;
	}
	
	/**
	 * Gets the list of positions that are adjacent to the given position.
	 * @return the list of adjacent positions
	 */
	public Collection<HantoCoordinate> adjacentPositions()
	{
		return HexUtil.locationsSurrounding(this);
	}
	
	/**
	 *  remove a piece from a place and return it.
	 * @param gameState the state of the game (for access to board and players)
	 * @param type the type of piece that the caller is looking for
	 * @return the piece removed from this powition
	 */
	public HantoTile removePieceAt(GameState gameState, HantoPieceType type)
	{
		return gameState.removePieceFrom(this);	
	}
}
