/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.studentotnah.common.util.HexUtil;

import java.util.Collection;
import java.util.ArrayList;

/**
 * Does various geometric work for determining distances and
 * position relative to other pieces.
 * 
 */
public abstract class Position implements HantoCoordinate
{
	
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

			@Override
			public boolean hasPieceType(GameState state, HantoPieceType type) {
				HantoPiece piece = state.getPieceAt(this);
				return piece != null && piece.getType() == type;
			}

			@Override
			public HantoCoordinate unwrap() {
				return other;
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
	 * Gets the list of coordinates that are adjacent to the given position.
	 * @return the list of adjacent coordinates
	 */
	public Collection<Position> adjacentPositions()
	{
		return HexUtil.locationsSurrounding(this);
	}
	
	/**
	 * Gets the list of tiles adjacent to this position in the given game state
	 * @param state the state of the game
	 * @return the list of adjacent tiles
	 */
	public Collection<HantoPiece> adjacentTiles(GameState state)
	{
		Collection<Position> coords = adjacentPositions();
		Collection<HantoPiece> positions = new ArrayList<>();
		for(HantoCoordinate n : coords)
		{
			HantoPiece t = state.getPieceAt(n);
			if (t != null)
			{
				positions.add(t);
			}
		}
		return positions;
	}
	
	/**
	 *  remove a piece from a place and return it.
	 * @param gameState the state of the game (for access to board and players)
	 * @param type the type of piece that the caller is looking for
	 * @param to 
	 * @return the piece removed from this powition
	 */
	public HantoTile movePieceFrom(GameState gameState, HantoPieceType type, Position to)
	{
		return gameState.removePieceFrom(this);
	}

	/**
	 * 
	 * @param state the game state
	 * @param type the type
	 * @return whether this location has that type
	 */
	public abstract boolean hasPieceType(GameState state, HantoPieceType type);
	
	/**
	 * convert back into an unwrapped form
	 * @return a coordinate
	 */
	public abstract HantoCoordinate unwrap();
	
	@Override
	public String toString()
	{
		return "("+getX()+","+getY()+")";
	}
}
